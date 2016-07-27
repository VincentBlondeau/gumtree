package com.github.gumtreediff.io.actionserializers;

import com.github.gumtreediff.actions.model.*;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;
import com.google.gson.stream.JsonWriter;
import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by VincentBlondeau on 12/04/2016.
 */
class MethodJsonFormatter implements ActionFormatter {
    private final JsonWriter writer;
    private final TreeContext context;
    private Set<String> alreadyExported;

    MethodJsonFormatter(TreeContext ctx, Writer writer) {
        this.writer = new JsonWriter(writer);
        this.writer.setIndent("  ");
        this.context = ctx;
        this.alreadyExported = new HashSet<String>();
    }

    @Override
    public void startActions() throws IOException {
        writer.beginArray();
    }

    @Override
    public void insertRoot(ITree node) throws IOException {
        start(Insert.class, node);
        end(node);
    }

    @Override
    public void insertAction(ITree node, ITree parent, int index) throws IOException {
        start(Insert.class, node);
        end(node);
    }

    @Override
    public void moveAction(ITree src, ITree dst, int index) throws IOException {
        start(Move.class, src);
        end(src);
    }

    @Override
    public void updateAction(ITree src, ITree dst) throws IOException {
        start(Update.class, src);
        //   writer.name("label").value(dst.getLabel());
        end(src);
    }

    @Override
    public void deleteAction(ITree node) throws IOException {
        start(Delete.class, node);
        end(node);
    }

    private void start(Class<? extends Action> name, ITree src) throws IOException {
        //System.out.println(src.toShortString());
        ASTNameConverter converter = new ASTNameConverter();
        ASTNode node = (ASTNode) src.getMetadata("ASTNode");
        node.accept(converter);
        String nameOfModifiedEntity = converter.getOutput();
        //System.out.println(node.getClass().getSimpleName() +":" + nameOfModifiedEntity);
        if (!nameOfModifiedEntity.equals("") && !alreadyExported.contains(nameOfModifiedEntity)) {
            writer.beginObject();
            //writer.name("label").value(src.getLabel());
            writer.name("type").value(name.getSimpleName());
            writer.name("astType").value(node.getClass().getSimpleName());
            writer.name("qualifiedParentName").value(nameOfModifiedEntity);
            alreadyExported.add(nameOfModifiedEntity);
            writer.endObject();
        }
    }

    private void end(ITree node) throws IOException {
        //writer.endObject();
    }

    @Override
    public void endActions() throws Exception {
        writer.endArray();
    }
}


package com.github.gumtreediff.io.actionserializers;

import com.github.gumtreediff.actions.model.*;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by VincentBlondeau on 12/04/2016.
 */
class JsonFormatter implements ActionFormatter {
    private final JsonWriter writer;

    JsonFormatter(TreeContext ctx, Writer writer) {

        this.writer = new JsonWriter(writer);
        this.writer.setIndent("  ");
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
        writer.name("parent").value(parent.getId());
        writer.name("at").value(index);
        end(node);
    }

    @Override
    public void moveAction(ITree src, ITree dst, int index) throws IOException {
        start(Move.class, src);
        writer.name("parent").value(dst.getId());
        writer.name("at").value(index);
        end(src);
    }

    @Override
    public void updateAction(ITree src, ITree dst) throws IOException {
        start(Update.class, src);
        writer.name("label").value(dst.getLabel());
        end(src);
    }

    @Override
    public void deleteAction(ITree node) throws IOException {
        start(Delete.class, node);
        end(node);
    }

    private void start(Class<? extends Action> name, ITree src) throws IOException {
        writer.beginObject();
        writer.name("action").value(name.getSimpleName().toLowerCase());
        writer.name("tree").value(src.getId());
    }

    private void end(ITree node) throws IOException {
        writer.endObject();
    }

    @Override
    public void endActions() throws Exception {
        writer.endArray();
    }
}


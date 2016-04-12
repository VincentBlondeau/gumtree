package com.github.gumtreediff.io.actionserializers;

import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by VincentBlondeau on 12/04/2016.
 */
class TextFormatter implements ActionFormatter {
    final Writer writer;
    final TreeContext context;

    public TextFormatter(TreeContext ctx, Writer writer) {
        this.context = ctx;
        this.writer = writer;
    }

    @Override
    public void startActions() throws Exception {
    }

    @Override
    public void insertRoot(ITree node) throws Exception {
        write("Insert root %s", toS(node));
    }

    @Override
    public void insertAction(ITree node, ITree parent, int index) throws Exception {
        write("Insert %s into %s at %d", toS(node), toS(parent), index);
    }

    @Override
    public void moveAction(ITree src, ITree dst, int position) throws Exception {
        write("Move %s into %s at %d", toS(src), toS(dst), position);
    }

    @Override
    public void updateAction(ITree src, ITree dst) throws Exception {
        write("Update %s to %s", toS(src), dst.getLabel());
    }

    @Override
    public void deleteAction(ITree node) throws Exception {
        write("Delete %s", toS(node));
    }

    @Override
    public void endActions() throws Exception {
    }

    private void write(String fmt, Object... objs) throws IOException {
        writer.append(String.format(fmt, objs));
        writer.append("\n");
    }

    private String toS(ITree node) {
        return String.format("%s(%d)", node.toPrettyString(context), node.getId());
    }
}


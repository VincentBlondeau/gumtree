package com.github.gumtreediff.io.actionserializers;

import com.github.gumtreediff.actions.model.*;
import com.github.gumtreediff.io.IndentingXMLStreamWriter;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.Writer;

/**
 * Created by VincentBlondeau on 12/04/2016.
 */

class XmlFormatter implements ActionFormatter {
    final TreeContext context;
    final XMLStreamWriter writer;

    XmlFormatter(TreeContext context, Writer w) throws XMLStreamException {
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        writer = new IndentingXMLStreamWriter(f.createXMLStreamWriter(w));
        this.context = context;
    }

    @Override
    public void startActions() throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeStartElement("actions");
    }

    @Override
    public void insertRoot(ITree node) throws Exception {
        start(Insert.class, node);
        end(node);
    }

    @Override
    public void insertAction(ITree node, ITree parent, int index) throws Exception {
        start(Insert.class, node);
        writer.writeAttribute("parent", Integer.toString(parent.getId()));
        writer.writeAttribute("at", Integer.toString(index));
        end(node);
    }

    @Override
    public void moveAction(ITree src, ITree dst, int index) throws XMLStreamException {
        start(Move.class, src);
        writer.writeAttribute("parent", Integer.toString(dst.getId()));
        writer.writeAttribute("at", Integer.toString(index));
        end(src);
    }

    @Override
    public void updateAction(ITree src, ITree dst) throws XMLStreamException {
        start(Update.class, src);
        writer.writeAttribute("label", dst.getLabel());
        end(src);
    }

    @Override
    public void deleteAction(ITree node) throws Exception {
        start(Delete.class, node);
        end(node);
    }

    @Override
    public void endActions() throws XMLStreamException {
        writer.writeEndElement();
        writer.writeEndDocument();
    }

    private void start(Class<? extends Action> name, ITree src) throws XMLStreamException {
        writer.writeEmptyElement(name.getSimpleName().toLowerCase());
        writer.writeAttribute("tree", Integer.toString(src.getId()));
    }

    private void end(ITree node) throws XMLStreamException {
//            writer.writeEndElement();
    }
}

package com.github.gumtreediff.io.actionserializers;

import com.github.gumtreediff.tree.ITree;

/**
 * Created by VincentBlondeau on 12/04/2016.
 */
interface ActionFormatter {
    void startActions() throws Exception;

    void insertRoot(ITree node) throws Exception;

    void insertAction(ITree node, ITree parent, int index) throws Exception;

    void moveAction(ITree src, ITree dst, int index) throws Exception;

    void updateAction(ITree src, ITree dst) throws Exception;

    void deleteAction(ITree node) throws Exception;

    void endActions() throws Exception;
}

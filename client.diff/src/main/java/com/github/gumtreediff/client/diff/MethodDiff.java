/*
 * This file is part of GumTree.
 *
 * GumTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GumTree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GumTree.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2011-2015 Jean-Rémy Falleri <jr.falleri@gmail.com>
 * Copyright 2011-2015 Floréal Morandat <florealm@gmail.com>
 */

package com.github.gumtreediff.client.diff;

import com.github.gumtreediff.actions.ActionGenerator;
import com.github.gumtreediff.actions.model.Action;
import com.github.gumtreediff.client.Register;
import com.github.gumtreediff.io.actionserializers.ActionsIoUtils;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import java.util.List;
import java.util.stream.Collectors;

@Register(name = "methoddiff", description = "Dump actions in the JSON format",
        options = AbstractDiffClient.Options.class)
public class MethodDiff extends AbstractDiffClient<AbstractDiffClient.Options> {

    public MethodDiff(String[] args) {
        super(args);
    }

    @Override
    protected Options newOptions() {
        return new Options();
    }

    @Override
    public void run() {
//        try {
//            File fSrc = Paths.get(opts.src).toFile();
//            File fDst = Paths.get(opts.dst).toFile();
//            TreeContext src = null;
//
//            src = Generators.getInstance().getTree(fSrc.getAbsolutePath());
//
//            TreeContext dst = Generators.getInstance().getTree(fDst.getAbsolutePath());
//            Matcher matcher = Matchers.getInstance().getMatcher(src.getRoot(), dst.getRoot());
//            matcher.match();
//            MethodHtmlDiffs diffs = new MethodHtmlDiffs(fSrc, fDst, src, dst, matcher);
//            diffs.produce();
//            System.out.println(diffs.getSrcDiff());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        MappingStore mappingStore = new MappingStore();
        boolean fakeTreeCreated = false;
        try {
            Matcher m = matchTrees();
            mappingStore = m.getMappings();
        } catch (UnsupportedOperationException e) {
            // With have to delete the fake tree we created
            fakeTreeCreated = true;
        }
//
//    }catch (UnsupportedOperationException e){
//        //Create fake tree that
//        TreeContext emptyTreeContext = new TreeContext();
//        ITree newTree = emptyTreeContext.createTree();
//        newTree.setId(-1);
//        emptyTreeContext.setRoot(newTree);
//        return emptyTreeContext;
//    }

        ITree srcTree;
        try {
            srcTree = getSrcTreeContext().getRoot();
        } catch (UnsupportedOperationException e2) {
            //Create fake tree that
            TreeContext emptyTreeContext1 = new TreeContext();
            srcTree = emptyTreeContext1.createTree(-1, "FAKE", "FAKE");
            srcTree.setId(-1);
            // emptyTreeContext.setRoot(newTree);
            // srcTree = emptyTreeContext;
        }

        ITree dstTree;
        try {
            dstTree = getDstTreeContext().getRoot();
        } catch (UnsupportedOperationException e1) {
            //Create fake tree that
            TreeContext emptyTreeContext = new TreeContext();
            dstTree = emptyTreeContext.createTree(-1, "FAKE", "FAKE");
            dstTree.setId(-1);
            // emptyTreeContext.setRoot(newTree);
            // srcTree = emptyTreeContext;
        }


        ActionGenerator g = new ActionGenerator(srcTree,
                dstTree, mappingStore);
        g.generate();
        List<Action> actions = g.getActions();
        try {
            if (fakeTreeCreated) {
                //remove the tree
                actions = actions.stream().filter(item -> (item.getNode().getId() != -1)).collect(Collectors.toList());
               // System.out.println(actions);
            }

            try {
                ActionsIoUtils.toMethodJson(getSrcTreeContext(), actions, mappingStore).writeTo(System.out);
            } catch (UnsupportedOperationException e) {
                //So it is maybe that source file doesn"t exists or is empty...
                ActionsIoUtils.toMethodJson(getDstTreeContext(), actions, mappingStore).writeTo(System.out);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

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
import com.github.gumtreediff.client.diff.ui.web.HtmlDiffs;
import com.github.gumtreediff.client.diff.ui.web.MethodHtmlDiffs;
import com.github.gumtreediff.gen.Generators;
import com.github.gumtreediff.io.actionserializers.ActionsIoUtils;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.matchers.Matchers;
import com.github.gumtreediff.tree.TreeContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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


        Matcher m = matchTrees();
        ActionGenerator g = new ActionGenerator(getSrcTreeContext().getRoot(),
                getDstTreeContext().getRoot(), m.getMappings());
        g.generate();
        List<Action> actions = g.getActions();
        try {
            ActionsIoUtils.toMethodJson(getSrcTreeContext(), actions, m.getMappings()).writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

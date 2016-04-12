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

package com.github.gumtreediff.io.actionserializers;

import com.github.gumtreediff.actions.model.*;
import com.github.gumtreediff.io.TreeIoUtils.AbstractSerializer;
import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.tree.TreeContext;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public final class ActionsIoUtils {

    private ActionsIoUtils() {
    }

    public static ActionSerializer toText(TreeContext sctx, List<Action> actions,
                                          MappingStore mappings) throws IOException {
        return new ActionSerializer(sctx, mappings, actions) {

            @Override
            protected ActionFormatter newFormatter(TreeContext ctx, Writer writer) throws Exception {
                return new TextFormatter(ctx, writer);
            }
        };
    }

    public static ActionSerializer toXml(TreeContext sctx, List<Action> actions,
                                         MappingStore mappings) throws IOException {
        return new ActionSerializer(sctx, mappings, actions) {

            @Override
            protected ActionFormatter newFormatter(TreeContext ctx, Writer writer) throws Exception {
                return new XmlFormatter(ctx, writer);
            }
        };
    }

    public static ActionSerializer toJson(TreeContext sctx, List<Action> actions,
                                              MappingStore mappings) throws IOException {
        return new ActionSerializer(sctx, mappings, actions) {

            @Override
            protected ActionFormatter newFormatter(TreeContext ctx, Writer writer) throws Exception {
                return new JsonFormatter(ctx, writer);
            }
        };
    }

    public abstract static class ActionSerializer extends AbstractSerializer {
        final TreeContext context;
        final MappingStore mappings;
        final List<Action> actions;

        ActionSerializer(TreeContext context, MappingStore mappings, List<Action> actions) {
            this.context = context;
            this.mappings = mappings;
            this.actions = actions;
        }

        protected abstract ActionFormatter newFormatter(TreeContext ctx, Writer writer) throws Exception;

        @Override
        public void writeTo(Writer writer) throws Exception {
            ActionFormatter fmt = newFormatter(context, writer);
            fmt.startActions();
            for (Action a : actions) {
                ITree src = a.getNode();
                if (a instanceof Move) {
                    ITree dst = mappings.getDst(src);
                    fmt.moveAction(src, dst.getParent(), ((Move) a).getPosition());
                } else if (a instanceof Update) {
                    ITree dst = mappings.getDst(src);
                    fmt.updateAction(src, dst);
                } else if (a instanceof Insert) {
                    ITree dst = a.getNode();
                    if (dst.isRoot())
                        fmt.insertRoot(src);
                    else
                        fmt.insertAction(src, dst.getParent(), dst.getParent().getChildPosition(dst));
                } else if (a instanceof Delete) {
                    fmt.deleteAction(src);
                }
            }
            fmt.endActions();
        }
    }
}

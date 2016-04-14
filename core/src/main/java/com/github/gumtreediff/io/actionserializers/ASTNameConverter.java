package com.github.gumtreediff.io.actionserializers;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by VincentBlondeau on 13/04/2016.
 */
public class ASTNameConverter extends ASTVisitor {

    private String output = "";
    protected Stack<String> fullyQualClass;

    public ASTNameConverter() {
        fullyQualClass = new Stack<>();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }


    public boolean visit(AbstractTypeDeclaration node) {
        ITypeBinding bnd = node.resolveBinding();
        if (bnd == null) {
            fullyQualClass.push("???;" + node.getName().getIdentifier());
        } else {
            fullyQualClass.push(bnd.getPackage().getName() + ";" + node.getName().getIdentifier());
        }
        output = fullyQualClass.peek();
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean visit(MethodDeclaration node) {
        Collection<String> paramTypes = ((List<SingleVariableDeclaration>) node.parameters()).stream()
                .map(param -> findTypeName(param.getType()))
                .collect(Collectors.toCollection(ArrayList::new));
        node.getParent().accept(this);
        output = fullyQualClass.peek() + ";" +
                getSig(node.resolveBinding(), node.getName().getFullyQualifiedName(), paramTypes);

        return false;
    }

    public String getSig(IMethodBinding bnd, String name, Collection<String> paramTypes) {

        // --------------- signature
        boolean first = true;
        String sig = "";
        if (bnd != null) {
            for (ITypeBinding parBnd : bnd.getParameterTypes()) {
                if (first) {
                    sig = parBnd.getName();
                    first = false;
                } else {
                    sig += "," + parBnd.getName();
                }
            }
        } else if (paramTypes != null) {
            for (String t : paramTypes) {
                if (first) {
                    sig = t;
                    first = false;
                } else {
                    sig += "," + t;
                }
            }
        } else {
            sig += "???";
        }
        sig = name + "(" + sig + ")";

        return sig;
    }

    private String findTypeName(org.eclipse.jdt.core.dom.Type t) {
        if (t == null) {
            return null;
        }

        if (t.isPrimitiveType()) {
            return t.toString();
        } else if (t.isSimpleType()) {
            return ((SimpleType) t).getName().getFullyQualifiedName();
        } else if (t.isQualifiedType()) {
            return ((QualifiedType) t).getName().getIdentifier();
        } else if (t.isArrayType()) {
            return findTypeName(((ArrayType) t).getElementType());
        } else if (t.isParameterizedType()) {
            return findTypeName(((org.eclipse.jdt.core.dom.ParameterizedType) t).getType());
        } else { // it is a WildCardType
            if (((org.eclipse.jdt.core.dom.WildcardType) t).isUpperBound()) {
                return findTypeName(((org.eclipse.jdt.core.dom.WildcardType) t).getBound());
            } else {
                return "Object";
            }
        }
    }

    public boolean visit(VariableDeclarationFragment astNode) {
        astNode.getParent().accept(this);
        output = fullyQualClass.peek() + "." + astNode.getName().getIdentifier();
        return false;
    }


    public boolean visit(TypeDeclaration node) {
        this.visit((AbstractTypeDeclaration) node);
        return false;
    }

    public boolean visit(EnumDeclaration node) {
        this.visit((AbstractTypeDeclaration) node);
        return false;
    }

    public boolean visit(AnnotationTypeDeclaration node) {
        this.visit((AbstractTypeDeclaration) node);
        return super.visit(node);
    }

    public boolean visit(TextElement astNode) {
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(SimpleType astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(TypeLiteral astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SimpleName astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SingleVariableDeclaration astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(QualifiedName astNode) {
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(ImportDeclaration astNode) {
        output = "Change Import Decl:" + astNode.getName().getFullyQualifiedName();
        return false;
    }

    public boolean visit(ParameterizedType astNode) {
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(ReturnStatement astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(BooleanLiteral astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(PrimitiveType astNode) {
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(Block astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(Modifier astNode) {
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(FieldDeclaration astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(EnumConstantDeclaration astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(AnonymousClassDeclaration astNode) {
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(CompilationUnit astNode) {
        return false;
    }


    public boolean visit(AnnotationTypeMemberDeclaration astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ArrayAccess astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ArrayCreation astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ArrayInitializer astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ArrayType astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(AssertStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(Assignment astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(BlockComment astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(BreakStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(CastExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(CatchClause astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(CharacterLiteral astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ClassInstanceCreation astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ConditionalExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ConstructorInvocation astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ContinueStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(CreationReference astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(Dimension astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(DoStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(EmptyStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(EnhancedForStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ExpressionMethodReference astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ExpressionStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(FieldAccess astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ForStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(IfStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(InfixExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(Initializer astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(InstanceofExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(IntersectionType astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(Javadoc astNode) {
        //Don't care about Javadoc
        return false;
    }

    public boolean visit(LabeledStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(LambdaExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(LineComment astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(MarkerAnnotation astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(MemberRef astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(MemberValuePair astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(MethodRef astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(MethodRefParameter astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(MethodInvocation astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(NameQualifiedType astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(NormalAnnotation astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(NullLiteral astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(NumberLiteral astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(PackageDeclaration astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ParenthesizedExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(PostfixExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(PrefixExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(QualifiedType astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(SingleMemberAnnotation astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(StringLiteral astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SuperConstructorInvocation astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(SuperFieldAccess astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(SuperMethodInvocation astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SuperMethodReference astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(SwitchCase astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SwitchStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SynchronizedStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(TagElement astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(ThisExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(ThrowStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(TryStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(TypeDeclarationStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(TypeMethodReference astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(TypeParameter astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(UnionType astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(VariableDeclarationExpression astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(VariableDeclarationStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(WhileStatement astNode) {

        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(WildcardType astNode) {

        astNode.getParent().accept(this);
        return false;
    }


}

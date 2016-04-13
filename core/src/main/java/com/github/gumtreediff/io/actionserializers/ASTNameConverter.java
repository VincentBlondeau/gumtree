package com.github.gumtreediff.io.actionserializers;

import org.eclipse.jdt.core.dom.*;

/**
 * Created by VincentBlondeau on 13/04/2016.
 */
public class ASTNameConverter extends ASTVisitor {

    private String output = "";

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean visit(TypeDeclaration astNode) {
        output = astNode.resolveBinding().getKey();
        return false;
    }

    public boolean visit(MethodDeclaration astNode) {
        output = astNode.resolveBinding().getKey();
        return false;
    }

    public boolean visit(TextElement astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(SimpleType astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(TypeLiteral astNode) {
        System.out.println("PLOP");
        return false;
    }

    public boolean visit(SimpleName astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(SingleVariableDeclaration astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(QualifiedName astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(ImportDeclaration astNode) {
        System.out.println("PLIP");
        output = "Change Import Decl:" + astNode.getName().getFullyQualifiedName();
        return false;
    }

    public boolean visit(ParameterizedType astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(ReturnStatement astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(BooleanLiteral astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(PrimitiveType astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(Block astNode) {
        System.out.println("PLIP");
        astNode.getParent().accept(this);
        return false;
    }

    public boolean visit(Modifier astNode) {
        System.out.println("PLOP");
        astNode.getParent().accept(this);
        return false;
    }


    public boolean visit(FieldDeclaration astNode) {
        System.out.println("PLOP");
        return false;
    }

    public boolean visit(EnumConstantDeclaration astNode) {
        System.out.println("PLOP");
        return false;
    }

    public boolean visit(AnonymousClassDeclaration astNode) {
        System.out.println("PLOP");
        return false;
    }

    public boolean visit(CompilationUnit astNode) {
        System.out.println("PLOP");
        return false;
    }


    public boolean visit(AnnotationTypeDeclaration astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(AnnotationTypeMemberDeclaration astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ArrayAccess astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ArrayCreation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ArrayInitializer astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ArrayType astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(AssertStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(Assignment astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(BlockComment astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(BreakStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(CastExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(CatchClause astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(CharacterLiteral astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ClassInstanceCreation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ConditionalExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ConstructorInvocation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ContinueStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(CreationReference astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(Dimension astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(DoStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(EmptyStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(EnhancedForStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(EnumDeclaration astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ExpressionMethodReference astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ExpressionStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(FieldAccess astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ForStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(IfStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(InfixExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(Initializer astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(InstanceofExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(IntersectionType astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(Javadoc astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(LabeledStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(LambdaExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(LineComment astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(MarkerAnnotation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(MemberRef astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(MemberValuePair astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(MethodRef astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(MethodRefParameter astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(MethodInvocation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(NameQualifiedType astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(NormalAnnotation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(NullLiteral astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(NumberLiteral astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(PackageDeclaration astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ParenthesizedExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(PostfixExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(PrefixExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(QualifiedType astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(SingleMemberAnnotation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(StringLiteral astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(SuperConstructorInvocation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(SuperFieldAccess astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(SuperMethodInvocation astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(SuperMethodReference astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(SwitchCase astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(SwitchStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(SynchronizedStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(TagElement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(ThisExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(ThrowStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(TryStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(TypeDeclarationStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(TypeMethodReference astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(TypeParameter astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(UnionType astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(VariableDeclarationExpression astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(VariableDeclarationStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(VariableDeclarationFragment astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


    public boolean visit(WhileStatement astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }

    public boolean visit(WildcardType astNode) {
         System.out.println("PLIP");         astNode.getParent().accept(this);         return false;
    }


}

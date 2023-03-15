package GUI.Utility;

import javafx.geometry.Insets;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Stack;

public class StateButton extends Button {

    private Background activeBackground;
    private Background inactiveBackGround;
    private Stack<Node> popStack;
    private Stack<Node> pushStack;
    private ScrollPane contentPane;


    public StateButton() {
        this.initialize();
    }

    public StateButton(String var1, Stack<Node> popStack, Stack<Node> pushStack, ScrollPane contentPane) {
        super(var1);
        this.popStack = popStack;
        this.pushStack = pushStack;
        this.contentPane = contentPane;
        this.activeBackground = new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(5)));
        this.inactiveBackGround = new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.02), new CornerRadii(5), new Insets(5)));
        this.setBackground(inactiveBackGround);
        this.initialize();
    }

    public StateButton(String var1, Node var2) {
        super(var1, var2);
        this.initialize();
    }

    private void initialize() {
        this.getStyleClass().setAll(new String[]{"button"});
        this.setAccessibleRole(AccessibleRole.BUTTON);
        this.setMnemonicParsing(true);
    }

    public void activateCounterStateEvent(StateButton pushButton) {
        this.setOnMouseClicked(e -> {
            if(!popStack.isEmpty()) {

                pushStack.push(contentPane.getContent());
                pushButton.setBackGroundActive();

                Node poppedElement = popStack.pop();

                contentPane.setContent(poppedElement);

                if(popStack.isEmpty()) {
                    this.setBackGroundInactive();
                }
            } else {
                this.setBackGroundInactive();
            }

            if(popStack.size() > 10) {
                cleanStack(popStack);
            } else if (pushStack.size() > 10) {
                cleanStack(pushStack);
            }
        });
    }

    public void cleanStack(Stack<Node> stack) {
        for(int i = stack.size() - 1; i > (stack.size() / 2); i--) {
            stack.removeElementAt(i);
        }
    }

    public void setBackGroundActive() {
        this.setBackground(activeBackground);
    }

    public void setBackGroundInactive() {
        this.setBackground(inactiveBackGround);
    }
}

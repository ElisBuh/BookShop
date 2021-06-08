
import api.view.IView;
import view.View;


public class Main {

    public static void main(String[] args) {
        IView iView = View.getViewInstance();
        iView.menu();
    }
}

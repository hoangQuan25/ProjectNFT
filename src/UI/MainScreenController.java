package UI;

import Functions.Function1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainScreenController {
	private Function1 fn1;
	
	public MainScreenController(Function1 fn1) {
		super();
		this.fn1 = fn1;
	}
	
	
    @FXML
    private Button btnFunction2;

    @FXML
    private Button btnFunction3;

    @FXML
    private Button btnFunction1;

}


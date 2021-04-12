package application;

class Presenter {

public void showA(Stage mainStage){
    ViewA a = new ViewA();
    a.setOnBackButton(new ViewCallback(){
        public void call(){
            showB();
        }
    });
    mainStage.setScene(new Scene(a));
}

public void showB(Stage mainStage){
    ViewB b = new ViewB();
    b.setOnBackButton(new ViewCallback(){
        public void call(){
            showA();
        }
    });
    mainStage.setScene(new Scene(b));
}

}
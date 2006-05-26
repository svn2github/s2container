package examples.hotdeploy.web.add;


public class AddAction {

    private AddPage addPage;

    public AddPage getAddPage() {
        return addPage;
    }

    public void setAddPage(AddPage addPage) {
        this.addPage = addPage;
    }
    
    public String doCalculate() {
        addPage.setResult(addPage.getArg1() - addPage.getArg2());
        return null;
    }
}

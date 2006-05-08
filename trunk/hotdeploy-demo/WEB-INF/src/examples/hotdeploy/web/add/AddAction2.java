package examples.hotdeploy.web.add;

import examples.hotdeploy.dto.AddDto2;

public class AddAction2 {

    private AddDto2 addDto;

    public AddDto2 getAddDto() {
        return addDto;
    }

    public void setAddDto(AddDto2 addDto) {
        this.addDto = addDto;
    }
    
    public String calculate() {
        addDto.setResult(addDto.getArg1() + addDto.getArg2());
        return null;
    }
}

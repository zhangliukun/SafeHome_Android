package cn.zalezone.domian;


//record the values

public class PatrolRadioGroup {
    
    private String recordName;
    private String firstOption;
    private String secondOption;
    private int value;//0 yes , 1 no
    
    public String getFirstOption() {
        return firstOption;
    }

    public void setFirstOption(String firstOption) {
        this.firstOption = firstOption;
    }

    public String getSecondOption() {
        return secondOption;
    }

    public void setSecondOption(String secondOption) {
        this.secondOption = secondOption;
    }

    
    public PatrolRadioGroup(String name,String first,String second,int value){
        this.recordName = name;
        this.firstOption = first;
        this.secondOption = second;
        this.value = value;
    }
    
    public String getRecordName() {
        return recordName;
    }
    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
    
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    
    
}

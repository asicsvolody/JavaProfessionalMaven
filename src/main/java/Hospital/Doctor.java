package Hospital;

public class Doctor {
    private SickList sickList;

    public void setSickList(SickList sickList) {
        this.sickList = sickList;
    }

    void inspection(){
        System.out.println("Начал осмотр");
        sickList.readSickList();
        System.out.println("Рекомендации");
    }


}

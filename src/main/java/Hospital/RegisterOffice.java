package Hospital;

public class RegisterOffice {

    public Doctor sendToDoctor(){
        Doctor doctor = new Doctor();
        doctor.setSickList(new SickList());
        return doctor;
    }


}

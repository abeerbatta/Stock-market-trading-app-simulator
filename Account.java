import org.json.JSONException;

public class Account {
    private String userName;
    private String password;
    private final String userId;
    private double balance;
    public String generateId() {
        final String abeer = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String s="";
        for(int i=0;i<4;i++) {
            int num=(int)(Math.random()*62);
            char ch=abeer.charAt(num);
            s+=ch;
        }
        return  s;
    }
    public String getPassword() {
        return password;
    }
    public Account(String userName,String password,double balance) {
        this.password=password;
        this.balance=balance;//For sign-up
        this.userId=generateId();
        this.userName=userName;
    }
    public Account(String userId,String password) {
        this.password=password;
        this.balance=0;//For login
        this.userId=userId;
        this.userName="";
    }
    public Account(String userId,String userName,String password,double balance) {
        this.password=password;
        this.balance=balance;
        this.userId=userId;
        this.userName=userName;
    }
    public String getUserId() {
        return this.userId;
    }
    public Account(String userId){
        this.userId = userId;
    }

    public double getBalance() throws JSONException {
        return this.balance;
    }
    public String getUserName() {
        return this.userName;
    }



}
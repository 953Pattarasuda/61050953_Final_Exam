import java.util.*;
import java.io.FileWriter;
import org.json.simple.JSONObject;

import jdk.internal.netscape.javascript.spi.JSObjectProvider;

class TransactionInfomation
{
    public int IDTransaction;
    public int IDOriginWallet;
    public int IDDestinationWallet;
    public float amount;
    public double exchange;
    public String status;

    public List<TransactionInfomation> transaction = new ArrayList<>();

    public Scanner sc = new Scanner(System.in);

    public TransactionInfomation(){}

    public TransactionInfomation(int IDTransaction, int IDOriginWallet, int IDDestinationWallet,float amount, double exchange, String status)
    {
        this.IDTransaction = IDTransaction;
        this.IDOriginWallet = IDOriginWallet;
        this.IDDestinationWallet = IDDestinationWallet;
        this.amount = amount;
        this.exchange = exchange;
        this.status = status;
    }

    public void insertTransaction()
    {
        
        // input data
        System.out.print("+++ID Transaction : "); IDTransaction = sc.nextInt();
        System.out.print("+++ID Origin Wallet : "); IDOriginWallet = sc.nextInt();
        System.out.print("+++ID Destination Wallet : "); IDDestinationWallet = sc.nextInt();
        System.out.print("+++Amount = "); amount = sc.nextFloat();
        System.out.print("+++Exchange : "); exchange = sc.nextDouble();
        
        setExchange(exchange,amount);

        

        // add data to ArrayList
        transaction.add(new TransactionInfomation(IDTransaction, IDOriginWallet, IDDestinationWallet, amount, exchange, "Awating"));
       
    }

    // check and set exchange
    public double setExchange(double exchange,float amount)
    {
        if(exchange < 100)
        {
            exchange = 100;
        }
        if(exchange > 150)
        {
            exchange = 150;
        }

        //process all money exchange
        double moneyExchange = amount * exchange;
        System.out.println("Money Exchange is "+ moneyExchange+" bath");
        return exchange;
    } 

}

class showAllTransaction extends TransactionInfomation
{
    // show All data in my ArrayList
    public void show()
    {
        System.out.println(transaction.toString());
    }
}

class SearchTransaction extends TransactionInfomation
{
    public TransactionInfomation findDaTransactionInfomation(int IDTransaction)
    {

    // read data have transction id = we need 
        for(TransactionInfomation transactions : transaction)
        {
            if(transactions.name.equals(IDTransaction))
            {
                return transactions;
                //System.out.println(transactions.toString());
            }
        }

        return null;
    
    }
}

class updateStatus extends TransactionInfomation
{
    public int indexx;
    public void updatedata(int IDTransaction,int choice)
    {
        SearchTransaction find = new SearchTransaction();
        List<TransactionInfomation> transactions = new ArrayList<>(); 
        
        // switch choice 1. complete , 2. incomplete
        switch(choice)
        {
            // case 1 change Awating to complete
            case 1 : transactions = find.findDaTransactionInfomation(IDTransaction);
                indexx = transaction.index(transactions);
                list = transactions + "complete";

                // set new data
                transaction.set(indexx, list);

            // case 2 change Awating to incomplete
            case 2 : transactions = find.findDaTransactionInfomation(IDTransaction);
                indexx = transaction.index(transactions);
                list = transactions + "incomplete";

                // set new data
                transaction.set(indexx, list);

        }
    }
}

class  CSKMITLCoin
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionInfomation transactioninfo = new TransactionInfomation();
        SearchTransaction search = new SearchTransaction();
        showAllTransaction showall = new showAllTransaction();
        updateStatus statuss = new updateStatus();
        while(true)
        {
            System.out.println("1. Make a Transaction\n2. Update status\n3. Search Transaction\n4. show all transaction\n5. Exit");
            System.out.print("Choose Number => ");
            int number = sc.nextInt();
            switch(number)
            {
                // insert transaction
                case 1 : transactioninfo.insertTransaction(); break;

                // update transaction status
                case 2 :
                    // show data and status we need
                    System.out.print("Enter ID Transaction => "); int IDTransaction = sc.nextInt();
                    search.findDaTransactionInfomation(IDTransaction);
                    // choose status you want to change 
                    System.out.print("Choose status => 1. complete\n2. incomplete"); int choice = sc.nextInt();
                    statuss.updatedata(IDTransaction, choice); break;
                    

                // find transaction we need
                case 3 : System.out.print("Enter ID Transaction => "); IDTransaction = sc.nextInt();
                    search.findDaTransactionInfomation(IDTransaction); break;
                
                // show all transaction
                case 4 : showall.show(); break;
                
                // close program
                case 5 : System.exit(0);
                
                // other case
                default : System.out.println("*********Please Check your Number***********\n");
            }
        }
        
    }
}
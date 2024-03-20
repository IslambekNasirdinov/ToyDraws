import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static final String FILENAME = "toys.csv";
    public static void main(String[] args) {
        try {
            Market market = new Market();
            File toyFile = new File(FILENAME);

            if(!toyFile.exists()){
                toyFile.createNewFile();
                market.addToys(new Toys(1,"Машина", 70,30));
                market.addToys(new Toys(2,"Мишка",100,35));
                market.addToys(new Toys(3,"Вини",50,40));
                market.saveToFile(FILENAME);
            }else {
                market.loadFile(FILENAME);
            }
            market.setWeight(2,80); // Изменение количество частота выпадения игрушки (вес в % от 100)
            ArrayList<String> toyList = market.getToyList();
            for(String toy : toyList){
                System.out.println(toy);
            }

            ArrayList<Toys> winners = market.playGame(1);
            for(Toys t : winners){
                System.out.println("Выиграли следующие игрушки: ");
                System.out.println(t.getName());
            }

            market.saveToFile(FILENAME);
        }catch (IOException e){
            System.out.println("Ошибка при работе с файлами: " +e.getMessage());
        }


    }
}
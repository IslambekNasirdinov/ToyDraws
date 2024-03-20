import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Market {
    private ArrayList<Toys> toys;

    public Market(){
        toys = new ArrayList<>();
    }

    public void addToys(Toys toy){
        boolean foundToy = false;
        for (Toys t : toys){
            if(t.getId() == toy.getId()){
                t.setQuantity(t.getQuantity() + toy.getQuantity());
                foundToy = true;
                break;
            }
        }
        if(!foundToy){
            toys.add(toy);
        }
    }

    public void setWeight(int toyId, double weight){
        for(Toys t :toys){
            if (t.getId() == toyId){
                t.setWeight(weight);
                break;
            }
        }
    }
    public ArrayList<String> getToyList(){
        ArrayList<String> toyList = new ArrayList<>();
        for (Toys t :toys){
            toyList.add("ID: " + t.getId() + "Название: " + t.getName() +
                    "Количество: " + t.getQuantity() + "Вес: " + t.getWeight());
        }
        return toyList;
    }
    public ArrayList<Toys> playGame(int count){
        ArrayList<Toys> winners = new ArrayList<>();
        double weightSum = 0;
        for(Toys t : toys){
            weightSum += t.getWeight();
        }
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            double randomNumber = random.nextDouble() * weightSum;
            double currentSum = 0;
            for(Toys t : toys){
                currentSum += t.getWeight();
                if(currentSum >= randomNumber){
                    if(t.getQuantity() > 0){
                        winners.add(t);
                        t.setQuantity(t.getQuantity() -1);
                        weightSum -= t.getWeight();
                    }
                    break;
                }
            }
            
        }
        return winners;
    }
    public void saveToFile(String fileName) throws IOException {
        try(FileWriter writer = new FileWriter(new File(fileName))) {
            for(Toys t :toys){
                writer.write(t.getId() + "," + t.getName() + "," +
                        t.getQuantity() + "," + t.getWeight() + "\n");
            }

        }

    }
    public void loadFile(String fileName) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] toyDate = line.split(",");
                Toys toy = new Toys(Integer.parseInt(toyDate[0]),toyDate[1],Integer.parseInt(toyDate[2]),Double.parseDouble(toyDate[3]));
                toys.add(toy);
            }

        }
    }


}

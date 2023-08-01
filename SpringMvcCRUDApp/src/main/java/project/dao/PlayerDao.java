package project.dao;

import org.springframework.stereotype.Component;
import project.models.Player;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlayerDao {

    private static int counter;

    private static List<Player> list;

    static {
        list = new ArrayList<>();
        list.add(new Player( "Tom",++counter));
        list.add(new Player( "Assan",++counter));
    }

    public List<Player> all() {
        return list;
    }

    public Player show(int id){
        return  list.stream().filter(x -> id == x.getNumber()).findAny().orElse(null);
    }

    public void save(int id, Player player){
        Player in = show(id);
        in.setName(player.getName());
    }

    public void delete(int id){
        list.removeIf(x -> x.getNumber() == id);
    }

    public void add(Player player){
        list.add(new Player(player.getName(), ++counter));
    }

}

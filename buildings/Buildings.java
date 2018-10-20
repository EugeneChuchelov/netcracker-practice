package buildings;

import java.io.*;

public class Buildings {
    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(building.getSize());
        for(Floor floor : building.toArray()){
            stream.writeInt(floor.getSize());
            for(Space space : floor.toArray()){
                stream.writeInt(space.getRoomsQuantity());
                stream.writeFloat(space.getArea());
            }
        }
    }

    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream stream = new DataInputStream(in);
        Floor[] floors = new Floor[stream.readInt()];
        for(int i = 0; i < floors.length; i++){
            floors[i] = new DwellingFloor(stream.readInt());
            for(int s = 0; s < floors[i].getSize(); s++){
                floors[i].getSpace(s).setRoomsQuantity(stream.readInt());
                floors[i].getSpace(s).setArea(stream.readFloat());
            }
        }
        return new Dwelling(floors);
    }

    public static void writeBuilding (Building building, Writer out) throws IOException {
        StringBuilder output = new StringBuilder();
        output.append(building.getSize()).append(" ");
        for(Floor floor : building.toArray()){
            output.append(floor.getSize()).append(" ");
            for(Space space : floor.toArray()){
                output.append(space.getRoomsQuantity()).append(" ");
                output.append(space.getArea()).append(" ");
            }
        }
        out.write(output.toString());
    }

    public static Building readBuilding (Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        Floor[] floors = new Floor[(int) tokenizer.nval];
        tokenizer.nextToken();
        for(int i = 0; i < floors.length; i++){
            floors[i] = new DwellingFloor((int) tokenizer.nval);
            tokenizer.nextToken();
            for(int s = 0; s < floors[i].getSize(); s++){
                floors[i].getSpace(s).setRoomsQuantity((int) tokenizer.nval);
                tokenizer.nextToken();
                floors[i].getSpace(s).setArea((float) tokenizer.nval);
                tokenizer.nextToken();
            }
        }

        return new Dwelling(floors);
    }

    public static void serializeBuilding (Building building, OutputStream out) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.writeObject(building);
    }

    public static Building deserializeBuilding (InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(in);
        return (Building) stream.readObject();
    }

    public static void writeBuildingFormat (Building building, Writer out){

    }
}

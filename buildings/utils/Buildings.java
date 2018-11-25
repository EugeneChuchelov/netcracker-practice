package buildings.utils;

import buildings.SynchronizedFloor;
import buildings.factories.DwellingFactory;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Buildings {
    private static BuildingFactory factory = new DwellingFactory();

    public static BuildingFactory getFactory() {
        return factory;
    }

    public static void setFactory(BuildingFactory factory) {
        Buildings.factory = factory;
    }

    public static Space createSpace(float area) {
        return factory.createSpace(area);
    }

    public static Space createSpace(int rooms, float area) {
        return factory.createSpace(rooms, area);
    }

    public static Floor createFloor(int spacesCount) {
        return factory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return factory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCount) {
        return factory.createBuilding(floorsCount, spacesCount);
    }

    public static Building createBuilding(Floor[] floors) {
        return factory.createBuilding(floors);
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream stream = new DataOutputStream(out);
        stream.writeInt(building.getSize());
        for (Floor floor : building.toArray()) {
            stream.writeInt(floor.getSize());
            for (Space space : floor.toArray()) {
                stream.writeInt(space.getRoomsQuantity());
                stream.writeFloat(space.getArea());
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream stream = new DataInputStream(in);
        Floor[] floors = new Floor[stream.readInt()];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[stream.readInt()];
            for (int s = 0; s < floors[i].getSize(); s++) {
                spaces[s] = factory.createSpace(stream.readInt(), stream.readFloat());//new Flat(stream.readInt(), stream.readFloat());
            }
            floors[i] = factory.createFloor(spaces);//new DwellingFloor(spaces);
        }
        return factory.createBuilding(floors);//new Dwelling(floors);
    }

    public static void writeBuilding(Building building, Writer out) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        StringBuilder output = new StringBuilder();
        output.append(building.getSize());
        for (Floor floor : building.toArray()) {
            output.append(" ").append(floor.getSize());
            for (Space space : floor.toArray()) {
                output.append(" ").append(space.getRoomsQuantity()).append(" ");
                output.append(space.getArea());
            }
        }
        try {
            writer.println(output.toString());
        } finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        float area;
        int rooms;
        tokenizer.nextToken();
        Floor[] floors = new Floor[(int) tokenizer.nval];
        tokenizer.nextToken();
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[((int) tokenizer.nval)];
            tokenizer.nextToken();
            for (int s = 0; s < spaces.length; s++) {
                //tokenizer.nextToken();
                rooms = (int) tokenizer.nval;
                tokenizer.nextToken();
                area = (float) tokenizer.nval;
                tokenizer.nextToken();
                spaces[s] = factory.createSpace(rooms, area);//new Flat(rooms, area);
            }
            floors[i] = factory.createFloor(spaces);//new DwellingFloor(spaces);
        }

        return factory.createBuilding(floors);//new Dwelling(floors);
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.writeObject(building);
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(in);
        return (Building) stream.readObject();
    }

    public static void writeBuildingFormat(Building building, Writer out) throws IOException {
        Formatter formatter = new Formatter(Locale.US);
        formatter.format("%d ", building.getSize());
        for (Floor floor : building.toArray()) {
            formatter.format("%d ", floor.getSize());
            for (Space space : floor.toArray()) {
                formatter.format("%d %4.2f ", space.getRoomsQuantity(), space.getArea());
            }
        }
        out.write(formatter.toString());
    }

    public static Building readBuilding(Scanner scanner) {
        Floor[] floors = new Floor[scanner.nextInt()];
        scanner.useLocale(Locale.US);
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[scanner.nextInt()];
            for (int s = 0; s < spaces.length; s++) {
                spaces[s] = factory.createSpace(scanner.nextInt(), scanner.nextFloat());//new Flat(scanner.nextInt(), scanner.nextFloat());
            }
            floors[i] = factory.createFloor(spaces);//new DwellingFloor(spaces);
        }
        return factory.createBuilding(floors);//new Dwelling(floors);
    }

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        T swapBuf;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swapBuf = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swapBuf;
                }
            }
        }
        return array;
    }

    public static <T> T[] sort(T[] array, Comparator<T> comparator) {
        T swapBuf;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    swapBuf = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swapBuf;
                }
            }
        }
        return array;
    }

    public static Floor synchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }

    public static Building parseBuilding(String in) {
        String[] tokens = in.split(" ");
        int current = 0;
        Floor[] floors = new Floor[Integer.parseInt(tokens[current++])];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[Integer.parseInt(tokens[current++])];
            for (int s = 0; s < spaces.length; s++) {
                spaces[s] = factory.createSpace(Integer.parseInt(tokens[current++]), Float.parseFloat(tokens[current++]));
            }
            floors[i] = factory.createFloor(spaces);
        }
        return factory.createBuilding(floors);
    }
}

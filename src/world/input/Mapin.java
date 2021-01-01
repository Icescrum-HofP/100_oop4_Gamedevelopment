package world.input;

import input.settings.Settings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Mapin {

    private String xmlpath;
    private int width;
    private int height;
    private int layers;
    private int[][][] contents;
    private Tile[][][] imgs;
    private File xmlFlie;
    private Tiles spritesheed;
    private Settings settings;

    public Mapin(String path){
        xmlpath = path;
        xmlFlie = new File(xmlpath);
        settings = new Settings();
    }

    private void xmlinput(){

        try {

            String split1 = "\n";
            String split2 = ",";
            String[] s1;
            String[] s2;

            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            Document doc = docB.parse(xmlFlie);

            NodeList layerList = doc.getElementsByTagName("layer");
            NodeList dataList = doc.getElementsByTagName("data");
            NodeList objList = doc.getElementsByTagName("objectgroup");

            Node whNode = layerList.item(0);
            this.layers = layerList.getLength();
            this.width = Integer.parseInt(((Element) whNode).getAttribute("width"));
            this.height = Integer.parseInt(((Element) whNode).getAttribute("height"));

            contents = new int[layers][height][width];
            imgs = new Tile[layers][height][width];

            for (int i = 0; i < layers; i++) {
                Node data = dataList.item(i);
                Element eData = (Element) data;

                if (data.getNodeType() == Node.ELEMENT_NODE) {
                    s1 = eData.getTextContent().trim().split(split1);

                    for (int j = 0; j < height; j++) {
                        s2 = s1[j].split(split2);

                        for (int k = 0; k < width; k++) {
                            contents[i][j][k] = Integer.parseInt(s2[k]);
                        }
                    }
                }
            }




        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }

    }

// map in tieles umwandeln
    private void tielmap(){
        String[] s = settings.getSpritesheedparameters();
        spritesheed =new Tiles(settings.getSpritesheedpath(),Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]));






    }









}

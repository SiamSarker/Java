import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewer {
    int xpos = 120, ypos = 120;
    void go() {
        JFrame frame = new JFrame("Image viewer");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel img1 = readImage("star.png", 50,50);
        img1.setBounds(xpos,ypos,50,50);
        frame.add(img1);

        frame.setVisible(true);






        // For image animation

//        int xpos = 250;
//        int ypos = 30;
//        for (int i = 0; i < 30; i++)
//        {
//            try {
//                Thread.sleep(20);
//                xpos -= 5;
//                ypos += 5;
//                img1.setBounds(xpos, ypos, 50, 50);
//
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }




        // input from keyboard

        int speed = 10;
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char pressedChar = e.getKeyChar();
                System.out.print(pressedChar);

                switch (pressedChar){
                    case 'w':
                        ypos -= speed;
                        break;
                    case 'd':
                        xpos += speed;
                        break;
                    case 's':
                        ypos += speed;
                        break;
                    case 'a':
                        xpos -= speed;
                        break;
                }
                img1.setBounds(xpos,ypos,50,50);
            }
        });


        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX()-8;
                int y = e.getY()-31;
                System.out.println(x+" "+y);
                img1.setBounds(x,y,50,50);
            }
        });




    }



    JLabel readImage(String imagpath, int width, int height)
    {
        try {
            BufferedImage image = ImageIO.read(new File(imagpath));

            Image imageScaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            return new JLabel(new ImageIcon(imageScaled));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        new ImageViewer().go();

    }

}

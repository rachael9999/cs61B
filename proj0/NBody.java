public class NBody {
    public static double readRadius(String path){
        In in = new In(path);
        in.readDouble();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String path){
        In in = new In(path);

        int num = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[num];
        int count = 0;

        while(count < num){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double ma = in.readDouble();
            String pic = in.readString();
            planets[count] = new Planet(xP,yP,vX,vY,ma,pic);
            count ++;
        }
        return planets;
    }
    public static void main (String[] args) {
        double T= Double.parseDouble(args[0]);
        double dt= Double.parseDouble(args[1]);
        String filename= args[2];
        Planet[] p=readPlanets(filename);
        double r = readRadius(filename);
        StdDraw.setScale(-r,r);

        double t=0;
        StdDraw.enableDoubleBuffering();
        while (t<T){
            double[] forceX = new double[p.length];
            double[] forceY = new double[p.length];

            for (int i=0; i<p.length;i++){
                forceX[i] = p[i].calcNetForceExertedByX(p);
                forceY[i] = p[i].calcNetForceExertedByY(p);
            }
            for (int i=0; i<p.length;i++){
                p[i].update(t,forceX[i],forceY[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet b: p) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }

}

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G=6.67e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet target){
        double distance = Math.sqrt((target.xxPos-xxPos)*(target.xxPos-xxPos)+(target.yyPos-yyPos)*(target.yyPos-yyPos));
        return distance;
    }
    public double calcForceExertedBy(Planet target){
        double Force= G*mass*target.mass/(calcDistance(target)*calcDistance(target));
        return Force;
    }
    public double calcForceExertedByX(Planet target){
        double Fx=calcForceExertedBy(target)*(target.xxPos-xxPos)/calcDistance(target);
        return Fx;
    }
    public double calcForceExertedByY(Planet target){
        double Fy=calcForceExertedBy(target)*(target.yyPos-yyPos)/calcDistance(target);
        return Fy;
    }
    public double calcNetForceExertedByX(Planet []p){
        double Fxnet=0.0;
                for(Planet s:p){
                    if (this.equals(s)) {
                    }
                    else{
                        Fxnet+=calcForceExertedByX(s);
                    }
                }
                return Fxnet;
    }
    public double calcNetForceExertedByY(Planet []p){
        double Fynet=0.0;
        for(Planet s:p){
            if (this.equals(s)) {
            }
            else{
                Fynet+=calcForceExertedByY(s);
            }
        }
        return Fynet;
    }
    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;

    }
    public void draw(){
        String file = "images/"+imgFileName;
        StdDraw.picture(xxPos,yyPos,file);
    }
}

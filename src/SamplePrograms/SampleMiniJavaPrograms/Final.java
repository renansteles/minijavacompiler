// CSE 401 MiniJava Test Program.  Au10
// Authors: Gaberiel Sprague and Jacob Masaki

// Should print out 10 to 42 in sequence if compiled correctly.  

class Hello {
  public static void main(String[] args){
    {
      System.out.println(1+9);  // 10 
      System.out.println(16-5); // 11
      System.out.println(2*6);  // 12
      System.out.println(new Goodbye().leave(16,17)); // 23 on return.  
      System.out.println(new Goodbye().reallyLeave(18)); // 25 on return
      if (true && false && (new Goodbye()).dontRun())
        System.out.println(666);
      else
        System.out.println(26); // 26
      System.out.println(new Goodbye().finallyLeave(27,29)); // 27, 28, 29 on return
      System.out.println(new Goodbye().waveGoodbye()); // 30, 31, 32, 33, 34 on return
      System.out.println(new JourneyHome().driveHome()); // 35, 36, 37, 38, 39, 40, 41 on return
      System.out.println(42); // End!
    }
  }
}

class Goodbye {
    public boolean dontRun() {
        System.out.println(666);
        return true;
    }
    public int leave(int x, int y) {
        int a;
        int b;
        boolean no;
        boolean yes;
        Goodbye g;
        a = 14;
        b = 18;
        System.out.println(a-1); // 13
        yes = true;
        no = false;
        g = new Goodbye();
        if(!yes){
            a = 666;
        }
        else {
            System.out.println(a); // 14
            a = a * 100;
            a = a - 1385;
            System.out.println(a); // 15
        }
        System.out.println(x);  // 16
        System.out.println(y);  // 17
        System.out.println(b);  // 18
        System.out.println(19); // 19
        x = 20;
        System.out.println(x); // 20
        System.out.println(g.reallyLeave(15)); // 22 on return
        return 23;
    }
    // Prints a + 6 and returns a + 7
    public int reallyLeave(int a) {
        a = a + 6;
        System.out.println(a); // 21 first time, 24 second time
        return a + 1;
    }
    public int finallyLeave(int low, int high) {
        while (low < high) {
            System.out.println(low);
            low = low + 1;
        }
        return high;
    }
    public int waveGoodbye(){
        Wave w;
        BigWave w2;
        int temp;

        w2 = new BigWave();
        w = w2;
        temp = w.init(30);
        System.out.println(w.wave()); // 30
        System.out.println(w2.highfive()); // 31
        temp = w2.bigInit(32);
        System.out.println(w2.bigWave()); // 32
        System.out.println(w2.wave()+3); // 33
        return w.wave()+4;
    }
   
}

class Wave {
    int num_waves;
    public int init(int numwaves) {
        num_waves = numwaves;
        return 0;
    }
    public int wave() {
        return num_waves;
    }
    public int highfive() {
        return 666;
    }
}

class BigWave extends Wave {
    int num_waves;
    public int bigInit(int numwaves) {
        num_waves = numwaves;
        return 0;
    }
    public int bigWave() {
        return num_waves;
    }
    public int highfive() {
        return 31;
    }
}

class JourneyHome {
    public int driveHome() {
        int[] car;
        car = new int[7];
        car[0] = 14;
        car[6] = 17;
        System.out.println(5 * car.length); // 35
        System.out.println(3 * car[0] - 6); // 36
        System.out.println(5 * car[6] - 48); // 37
        System.out.println(car[0] + car[6] + 7); // 38
        car[1] = car[0] + car[6];
        System.out.println(car[1] + 8); // 39
        car[1] = car[1] + 9;
        System.out.println(car[1]); // 40
        return 41;
    }   
}
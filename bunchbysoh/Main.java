package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    int ratedCap=120; //all the batteries are having a rated capacity of 120 Ah.
    //Looping through each of the battery's present capacity and further classifying based of SoH.
    for(int capacity: presentCapacities){
      double SOH=100.0*capacity/ratedCap;
      if(SOH>83){
        counts.healthy++;
      }else if(SOH>=63){
        counts.exchange++;
      }
      else{
        counts.failed++;
      }
      
    }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);

    //testing for boundary conditions
    int[] boundaryCap={62,63,83,130};
    counts=countBatteriesByHealth(boundaryCap);

    //expected values for these edge cases are listed below
    assert(counts.healthy==1); //130 is healthy
    assert(counts.exchange==2); //63 and 83 are in exchange
    assert(counts.failed==1); //62 is failed
    
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}

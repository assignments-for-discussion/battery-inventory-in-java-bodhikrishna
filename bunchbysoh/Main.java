package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    if(presentCapacities.length == 0)
    {
      return counts; //if no battery is provided, then simply return the count.
    }
    final double RATED_CAPACITY=120.0; //rated capacity defined as constant =120 Ah.
    //Looping through each of the battery's present capacity and further classifying based of SoH.
    for(int capacity: presentCapacities){
      double SOH=(100.0*capacity)/RATED_CAPACITY;
      if(SOH>83){ //SoH greater than 83% upto 100%: healthy category
        counts.healthy++;
      }else if(SOH>=63){ //Soh between range of 63% to 83%:exchange category
        counts.exchange++;
      }
      else{ //Soh strictly below 63%: failed category
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

    int[] testCase1 = {110,88,40};
    counts = countBatteriesByHealth(testCase1);
    assert(counts.healthy==1); //110 is healthy
    assert(counts.exchange==1); //88 is exchange
    assert(counts.failed==1); //40 is failed
    System.out.println("Test Case 1 passed!");


    //testing for boundary conditions
    int[] boundaryCap={62,63,83,100};
    counts=countBatteriesByHealth(boundaryCap);
    //expected values for these edge cases are listed below
    assert(counts.healthy==1); //100 is healthy
    assert(counts.exchange==1); //83 is exchange
    assert(counts.failed==2); //62 and 63 is failed
    System.out.println("Boundary testcases passed");

    int[] empty={} //handling case where no battery provided
    counts=countBatteriesByHealth(empty);
    assert(counts.healthy==0);
    assert(counts.exchange==0);
    assert(counts.failed==0);
    System.out.println("Empty array testcases passed");
    System.out.println("Done counting :)\n");


    
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}

package harry.tan.test;

import harry.tan.serviceImpl.TrainsServiceImpl;

public class TestMain {

	public static void main(String[] args) {
		
		TrainsServiceImpl m = new TrainsServiceImpl();
//		int dd = m.getDistance("C-D-C-E");
//		String result = dd > 0 ? dd+"": "NO SUCH ROUTE";
//		System.out.println(result);
		
//		int dd = m.getShortDistance("A", "C");
//		System.out.println(dd);
		
		m.gitShortestDistance("A", "C");
		
	}
	
	
	

}

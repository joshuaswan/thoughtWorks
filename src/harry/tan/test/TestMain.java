package harry.tan.test;

import harry.tan.serviceImpl.TrainsServiceImpl;

public class TestMain {

    /**
     * http://blog.csdn.net/javaman_chen/article/details/8254309
    * <method description>
    *
    * @param args
     */
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

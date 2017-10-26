import java.io.File;

import opt.Minimizer;
import poly.Polynomial;
import util.Vector;
import poly.Term;

/** This is a small example of test cases.  To test correctness of your implementation,  
 *  see if the output on your tests matches the results of the same tests on the solution
 *  in TestOptSoln.  Note the imports in this file!
 * 
 *  NOTE: You need to write your own test cases for full credit -- we will ask to see
 *        your additional test cases during code review.
 *
 * @author ssanner@mie.utoronto.ca
 *
 */
public class TestOpt {

	public static void main(String[] args) throws Exception {
		// You must run more test cases than this!
		RunMinimizer("files/poly1.txt", 0.001, 200, 0.10, "{ x=1.0 }");
		RunMinimizer("files/poly2.txt", 0.001, 200, 0.10, "{ x=1.0 y=1.0 }");
        
                Vector case1 = new Vector ("{ x=7 z=3 a=-5 }");
                System.out.println("This should print { a=-5.0000 x=7.0000 z=3.0000 }: "+case1);
          
                Vector case2 = new Vector ("{ x=4 z=5 a=6 }");       
                System.out.println("This should print { a=1.0000 x=11.0000 z=8.0000 }: "+case1.sum(case2));
                System.out.println("This should print { a=4.0000 x=44.0000 z=32.0000 }: "+ case1.sum(case2).scalarMult(4));
                
                Term case3 = new Term("x*y^5*z^2");
                System.out.println("This should print 2.000*x*y^5*z: "+case3.differentiate("z"));
                Vector case4 = new Vector ("{ x=2 y=3 z=4 }");
                System.out.println("This should print 7776.0: "+case3.evaluate(case4));
                
                Polynomial case5  = new Polynomial("x^3 + y^6 + 3*x*y");
                System.out.println("This should print 755.0: "+case5.evaluate(case4));
                       

        }	

	public static void RunMinimizer(String polyfile, double eps, int max_iter, double alpha, String sx0) 
			throws Exception {
		
		Minimizer m = new Minimizer();

		// If the following file does not load, verify that it exists,
		// and check that it is the correct path relative to your
		// NetBeans/Eclipse project settings for working directory
		Polynomial p = Polynomial.ReadPolynomial(new File(polyfile));
		
		m.setEps(eps);
		m.setMaxIter(max_iter);
		m.setStepSize(alpha);
		m.setX0(new Vector(sx0));
		
		System.out.println("========================================");
		System.out.println("OPTIMIZING: " + p);
		System.out.println("========================================");
		m.printParams(System.out);
		m.minimize(p);
		m.printResults(System.out);
	}
}

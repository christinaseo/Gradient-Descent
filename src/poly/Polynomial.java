package poly;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import util.Vector;

/** Implements a polynomial as a sum of terms.  If 5x^2 + 3xy is a polynomial,
 *  it has two Terms 5x^2 and 2xy, each stored in the member list _terms.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class Polynomial {
    
    

	private ArrayList<Term> _terms; // The Polynomial is the sum of these Terms

	/** This constructor has been implemented for you.  It simply initializes an
	 *  empty term list.
	 * 
	 */
	public Polynomial() {
		_terms = new ArrayList<>();
	}
	
	/** This constructor has been implemented for you -- it parses a term 
	 *  representation from a String into the format required by this class.
	 * 
	 * @param s -- String to parse
	 * @throws PolyException if s is malformed
	 */
	public Polynomial(String s) throws PolyException {

		if (s == null || s.trim().equals(""))
			throw new PolyException("Empty Polynomial, cannot read");
		_terms = new ArrayList<>();
		String[] terms = s.split("\\+");
		for (String term : terms)
			_terms.add(new Term(term));
	}
        
        public ArrayList<Term> getTerms()  {
        return this._terms;
        }
	
	/** Produce a re-parseable representation of this Polynomial as a String.  This
	 *  has been done for you.
	 * 
         * @return 
	 */
        @Override
	public String toString() {
		// Using "+" to append Strings involves a lot of String copies since Strings are 
		// immutable.  StringBuilder is much more efficient for append.
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Term term : _terms) {
			sb.append((first ? "" : " + ") + term);
			first = false;
		}
		return sb.toString();
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// TODO: Your methods here!  You should add some helper methods that facilitate
	//       the implementation of the methods below.
	///////////////////////////////////////////////////////////////////////////////

	/** This method takes a file (e.g., new File("files/poly1.txt")) which on its
	 *  first line should contain a syntactically correct Polynomial as parsed by 
	 *  new Polynomial(String s) above and return that Polynomial.
	 *  
	 *  You need to implement this method... it requires file I/O!
	 * 
	 * @param file
	 * @return
	 * @throws PolyException if there were any errors reading or parsing the file
         * @throws FileNotFoundException
	 */
	public static Polynomial ReadPolynomial(File file) throws PolyException, FileNotFoundException, IOException {
            BufferedReader fin = new BufferedReader(new FileReader(file));
            Polynomial _nPoly = new Polynomial(fin.readLine()); 
            return _nPoly;
	}
	
	/** Returns all of the variables used in this Polynomial as a sorted set (TreeSet).
	 * 
	 * @return (TreeSet of Strings as defined above)
	 */
	public TreeSet<String> getAllVars() {
            TreeSet<String> tree = new TreeSet<>();
            // for loop to iterate through the terms
            for(int i=0;i<_terms.size();i++) 
                //for loop to iterate through variables in each term
                for(int j = 0; j <_terms.get(i).sizeofVars();j++)
                    tree.add(_terms.get(i).getVars(j)); 
	return tree;
	}
	
	/** If Polynomial defines f(x,y) = 2xy^2 + xy and assignments is { x=2.0 y=3.0 } 
	 *  then this method returns 42.0, which is the evaluation of f(2.0,3.0).  
	 *  Incidentally, this is also the "Answer to the Ultimate Question of Life, the 
	 *  Universe, and Everything" in case you were wondering.
	 * 
	 * @param assignments
	 * @return
	 * @throws PolyException
	 */
	public double evaluate(Vector assignments) throws Exception {
            double answer = 0;
            //answer = function with the assignments subbed in
            for (Term t : this._terms)
                answer += t.evaluate(assignments);                                   
	return answer;
	}

	/** If Polynomial defines a function f(.) then this method returns the **symbolic**
	 *  partial derivative (which you can verify from calculus is still a Polynomial):
	 *  
	 *    partial f(1.0,2.0) / partial var.
	 * 
	 *  Specifically, if Polynomial defines f(x,y) = 2xy^2 + xy and var = "x"
	 *  then this method returns a **new** Polynomial 2y^2 + y and if var = "y" 
	 *  then it instead returns a **new** Polynomial 4xy + x.
	 * 
	 * @param var
	 * @return partial derivative of this w.r.t. var as a new Term
         * @throws java.lang.Exception
	 */
	public Polynomial differentiate(String var) throws Exception {
                Polynomial newPoly = new Polynomial();
                for(Term t: _terms){
                    Term dt = t.differentiate(var);
                    if(dt.getCoef()!=0.0d){
                        newPoly._terms.add(dt);
                        
                    }
                }
                //for (int i = 0; i < this._terms.size(); i++)
                    //for (int j = 0; j < _terms.get(i).sizeofVars(); j++) //doesnt like me adding in the extra for loop either
                       //if (_terms.get(i).getVars(j).equals(var)) //error if (_terms.get(i).getVar(i).equals(var))                        
                    //newPoly._terms.add(i,_terms.get(i).differentiate(var)); 
        return newPoly;
	}

	/** Some examples testing the Polynomial and Term classes with expected output.
	 *  The functionality below will be tested standalone for grading.
	 *  
	 *  When initially developing the code, comment out lines below that you have
	 *  not implemented yet.  This will allow your code to compile for incremental
	 *  testing.
	 * 
	 * @param args
	 * @throws Exception if any errors occur (when implemented correctly, there should
	 *         be no Exceptions/errors below)
	 */
	public static void main(String[] args) throws Exception {
		Polynomial p  = new Polynomial("x^2 + y^2 + -4*x*y + 8");
		Polynomial p2 = new Polynomial(p.toString()); // See if we can reparse p.toString()
		Polynomial dp_dx = p.differentiate("x");
		Polynomial dp_dy = p.differentiate("y");

                //Build a point vector (HashMap) of numerical assignments for variables
		Vector x0 = new Vector();
		x0.set("x", 1.0);
		x0.set("y", 2.0);
		
                
		System.out.println("Polynomial: " + p);     // Should print "1.000*x^2 + 1.000*y^2 + -4.000*x*y + 8.000"
		System.out.println("Re-parsed:  " + p2);    // Should print "1.000*x^2 + 1.000*y^2 + -4.000*x*y + 8.000"
		System.out.println("dp/dx:      " + dp_dx); // Should print "2.000*x + -4.000*y"
		System.out.println("dp/dy:      " + dp_dy); // Should print "2.000*y + -4.000*x"
		System.out.println("Free vars:  " + p.getAllVars()); // Should print "[x, y]"
                //System.out.println(x0);
		System.out.println("p(x0)     = " + p.evaluate(x0));     // Should print "5.0"
		System.out.println("dp/dx(x0) = " + dp_dx.evaluate(x0)); // Should print "-6.0"
		System.out.println("dp/dy(x0) = " + dp_dy.evaluate(x0)); // Should print "0.0"

        }
}

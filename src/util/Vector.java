package util;

import java.util.HashMap;

/** Implements a vector with *named* indices.  For example { x=1.0 y=2.0 } is a 2D
 *  vector with the first dimension named "x" and the second dimension named "y"
 *  and having respective values 1.0 and 2.0 in these dimensions.
 *  
 *  TODO: Implement all methods required to support the functionality of the project
 *        and that described in Vector.main(...) below.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class Vector {

	private HashMap<String,Double> _hmVar2Value; // This maps dimension variable names to values
	
	/** Constructor of an initially empty Vector
	 * 
	 */
	public Vector() {
            _hmVar2Value = new HashMap<>();
	}

	/** Constructor that parses a String s like "{ x=-1 y=-2.0 z=3d }" into 
	 *  the internal HashMap representation of the Vector.  See usage in main().
	 * 
	 * @param s
         * @throws java.lang.Exception
	 */
	public Vector(String s) throws Exception {
            _hmVar2Value = new HashMap<>();
            String[] _mParts = s.split(" ");
            for (int i = 1; i < _mParts.length -1; i++){ 
                String[] _mParts1 = _mParts[i].split("=");
                set(_mParts1[0],Double.parseDouble(_mParts1[1]));}                                 
            //for (int i = 0; i < _mParts1.length; i+=2)
               //set(_mParts1[i],Double.parseDouble(_mParts1[i+1]));
            // or this.put(_mParts1[i],Double.parseDouble(_mParts1[i+1]));
               //this.set(_mParts1[i], Double.parseDouble(_mParts1[i+1]));
	}

	/** Removes (clears) all (key,value) pairs from the Vector representation
	 * 
	 */
	public void clear() {
            _hmVar2Value.clear(); 
	}

	/** Sets a specific var to the value val in *this*, i.e., var=val
	 * 
	 * @param var - label of Vector index to change
	 * @param val - value to change it to
         * @throws java.lang.Exception
         * @throws NullPointerException
	 */
	public void set(String var, double val) throws Exception  {
            _hmVar2Value.put(var,val);
	}

	/** Sets all entries in *this* Vector to match entries in x
	 *  (if additional variables are in *this*, they remain unchanged) 
	 * 
	 * @param x
	 */
	public void setAll(Vector x) {
            _hmVar2Value.putAll(x._hmVar2Value);
        }
        
        //tostring function
        	/** Overrides method toString() on Object: converts the class to a human readable String
	 * 
	 *  Note 1: this is invoked *automatically* when the object is listed where a String is expected,
	 *          e.g., "System.out.println(v);" is actually equivalent to "System.out.println(v.toString());"       
	 *          
	 *  Note 2: for debugging purposes, you should always define a toString() method on a class you define
	 * @param var
         * @param val
         * @return
         */
        @Override
	public String toString() {
		// We could just repeatedly append to an existing String, but that copies the String each
		// time, whereas a StringBuilder simply appends new characters to the end of the String
		StringBuilder sb = new StringBuilder();
		sb.append("{");
                for(String var:_hmVar2Value.keySet())
                    sb.append(String.format(" %s=%6.4f", var, this._hmVar2Value.get(var))); 
		sb.append(" }");
		return sb.toString();
	}
        
        //sum function
        public Vector sum(Vector v) throws Exception {
            Vector vec = new Vector();
            for (String s : _hmVar2Value.keySet())
             vec.set(s,_hmVar2Value.get(s) + v._hmVar2Value.get(s)); 
            return vec;
        }
        //scalarmult function
        /** This creates a new Vector, multiplies it by a scalar d, and returns it
	 *  (should not modify *this*)
	 * 
	 * @param d
	 * @return new Vector after scalar addition
     * @throws java.lang.Exception
	 */
	public Vector scalarMult(double d) throws Exception {
		Vector n = new Vector(); 
                for (String s : _hmVar2Value.keySet())
                    n.set(s, _hmVar2Value.get(s)*d);
	return n;
	}
        //computeL2Norm function NEEDS TO BE FIXED!!! cant assume 3D/x,y,z is the largest case
        public double computeL2Norm(){
            double norm = 0;
            for (String s : _hmVar2Value.keySet())
                norm += _hmVar2Value.get(s) * _hmVar2Value.get(s);
            //norm = Math.sqrt((_hmVar2Value.get("x"))*(_hmVar2Value.get("x")) + (_hmVar2Value.get("y"))*(_hmVar2Value.get("y")) + (_hmVar2Value.get("z"))*(_hmVar2Value.get("z")));
            norm = Math.sqrt(norm);
            return norm;
        }
        
        //allow other classes to access the hashmap
        public HashMap<String,Double> getMap(){
            return _hmVar2Value;
        }
        
        @Override // optional annotation to tell Java we expect this overrides a parent method -- compiler will warn if not
	public boolean equals(Object o) {
		if (o instanceof Vector) {
			Vector v = (Vector)o; // This is called a cast (or downcast)... we can do it since we
			if (v._hmVar2Value.size() != this._hmVar2Value.size())
                            return false;
                        //System.out.println(v);
                        //System.out.println(this);
			for (String index : this._hmVar2Value.keySet()) {
                        if (!(v._hmVar2Value.containsKey(index))) 
                            return false;
			if (!(v._hmVar2Value.get(index).equals(this._hmVar2Value.get(index))))
					return false; // If two Vectors mismatch at any index, they are not equal
			
                        }return true; // Everything matched... objects are equal!
                        
		}
                else // if we get here "(o instanceof Vector)" was false
			return false; // Two objects cannot be equal if they don't have the same class type
	}

	///////////////////////////////////////////////////////////////////////////////
	// TODO: Add your methods here!  You'll need more than those above to make
	//       main() work below.
	///////////////////////////////////////////////////////////////////////////////
	
	/** Your Vector class should implement the core functionality below and produce
	 *  **all** of the expected outputs below.  **These will be tested for grading.**
	 * 
	 *  When initially developing the code, comment out lines below that you have
	 *  not implemented yet.  This will allow your code to compile for incremental
	 *  testing.
	 *  
	 * @param args (unused -- ignore)
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Make vector: vec1[x y z] = [1 2 3]
		Vector vec1 = new Vector();
		vec1.set("x", 1.0);
		vec1.set("y", 2.0);
		vec1.set("z", 3.0);
		
		// Make vector: vec2[x y z] = [-3 -2 -1]
		Vector vec2 = new Vector();
		vec2.set("x", -3.0);
		vec2.set("y", -2.0);
		vec2.set("z", -1.0);
		
		// Make vector: vec3[x y z] = vec4[x y z] = [-1 -2 -3]
		Vector vec3 = new Vector("{ x=-1 y=-2.0 z=3d }");
		Vector vec4 = new Vector(vec3.toString());
		
		// Hint: all numbers below are formatted with String.format("%s=%6.4f ", var, val)
		//       ... you may want to use this in your Vector.toString() implementation!
		
		// Test cases: 
		System.out.println(vec1); // Should print: { x=1.0000 y=2.0000 z=3.0000 }
		System.out.println(vec2); // Should print: { x=-3.0000 y=-2.0000 z=-1.0000 }
		System.out.println(vec3); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec4); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec1.sum(vec1));        // Should print: { x=2.0000 y=4.0000 z=6.0000 }
		System.out.println(vec1.sum(vec2));        // Should print: { x=-2.0000 y=0.0000 z=2.0000 }
		System.out.println(vec1.sum(vec3));        // Should print: { x=0.0000 y=0.0000 z=6.0000 }
		System.out.println(vec1.scalarMult(0.5));  // Should print: { x=0.5000 y=1.0000 z=1.5000 }
		System.out.println(vec2.scalarMult(-1.0)); // Should print: { x=3.0000 y=2.0000 z=1.0000 }
		System.out.println(vec1.sum(vec2.scalarMult(-1.0))); // Should print: { x=4.0000 y=4.0000 z=4.0000 }
		System.out.format("%01.3f\n", vec1.computeL2Norm());           // Should print: 3.742
		System.out.format("%01.3f\n", vec2.sum(vec3).computeL2Norm()); // Should print: 6.000
		
		// If the following don't work, did you override equals()?  See Project 2 Vector and Matrix.
		System.out.println(vec3.equals(vec1)); // Should print: false
		System.out.println(vec3.equals(vec3)); // Should print: true
		System.out.println(vec3.equals(vec4)); // Should print: true
		System.out.println(vec1.sum(vec2).equals(vec2.sum(vec1))); // Should print: true
	}	
}

/*
 * Copyright 2011 by Graz University of Technology, Austria
 * MOCCA has been developed by the E-Government Innovation Center EGIZ, a joint
 * initiative of the Federal Chancellery Austria and Graz University of Technology.
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * http://www.osor.eu/eupl/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * This product combines work with different licenses. See the "NOTICE" text
 * file for details on the various modules and licenses.
 * The "NOTICE" text file is part of the distribution. Any derivative works
 * that you distribute must include a readable copy of the "NOTICE" text file.
 */



package at.gv.egiz.smcc;

public class SignatureCardException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Creates a new instance of this <code>SignatureCardException</code>.
   * 
   */
  public SignatureCardException() {
    super();
  }

  /**
   * Creates a new instance of this <code>SignatureCardException</code>.
   * 
   * @param message
   * @param cause
   */
  public SignatureCardException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates a new instance of this <code>SignatureCardException</code>.
   * 
   * @param message
   */
  public SignatureCardException(String message) {
    super(message);
  }

  /**
   * Creates a new instance of this <code>SignatureCardException</code>.
   * 
   * @param cause
   */
  public SignatureCardException(Throwable cause) {
    super(cause);
  }

  
  
}
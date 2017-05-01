package gameutil;
/**
* CommutativeExpressionReader.java
 * 
 * yu hu
 **/

// XML
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;   
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;  
import org.w3c.dom.*;

import database.BinaryTree;
import database.BinaryTreeNode;
import database.DefaultBinaryTree;
import database.DefaultBinaryTreeNode;


// io
import java.io.*;

/**
 * CommutativeExpressionReader reads xml files of expressions with
 * binary, commutative operators.
 *
 * @author yu hu
 */
public class questionReader
{
   /**
    * Parses XML file.
    * @return BinTree corresponding to file.
    **/
   public static BinaryTree<String> readQuestion( String file )
   {
     return readQuestion( new File( file ) );
   }

   /**
    * Parses XML file
    * @return  BinTree corresponding to file.
    **/
   public static BinaryTree<String> readQuestion( File file )
   {
      DocumentBuilderFactory factory =
         DocumentBuilderFactory.newInstance();
      
      try 
      {
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document document = builder.parse( file );

         return parseQuestionTree( document );	    
         
      } 
      catch (SAXException sxe) 
      {
         // Error generated during parsing)
         Exception  x = sxe;
         if (sxe.getException() != null)
            x = sxe.getException();
         x.printStackTrace();         
      } 
      catch (ParserConfigurationException pce) 
      {
         // Parser with specified options can't be built
         pce.printStackTrace();
      }   
      catch (IOException ioe) 
      {
         // I/O error
         ioe.printStackTrace();
      }
      
      return null;
   }
   
   /**
    * Parses XML Document. 
    * @return parsed BinaryTree.
    **/
   private static BinaryTree<String> parseQuestionTree( Document document )
   {
	   BinaryTree<String> tree = new DefaultBinaryTree<String>();

     // parse root
     Element root = (Element)document.getDocumentElement();
     
     tree.setRoot( parseQuestionNode( root ) );

     return tree;
   }
   
  /**
   * Parses question element.
   * leaf are answer element
   * @return BinTreeNode represented by element.
   **/
  private static BinaryTreeNode<String> parseQuestionNode( Element element )
  {
    // base case: atom
    if ( element.getNodeName().equals( "Answer" ) )
    {
         // get attribute by name
         BinaryTreeNode<String> node = new DefaultBinaryTreeNode<String>();
         node.setData(element.getAttribute( "text" ));
         return node;  
         
    }
    
    
    // recursive case
    else
    {
      // get children
      NodeList children = element.getChildNodes();      

      // first node is leftChild, second node is rightChild
      //first node is yes, second is no
      
      String question = "";
      BinaryTreeNode<String> left = null;
      BinaryTreeNode<String> right = null;
      Element currentElt;

      question = element.getAttribute("text");
      
        // instant variable
      int count = 0;
      
      for(int i = 0; i < children.getLength(); i++){
    	 
        if ( children.item(i) instanceof Element )
        {
          currentElt = (Element)children.item(i);
          //leftChild
          if(count == 0){
              left = parseQuestionNode( currentElt );
          	  count ++;
          }else{
          //rightChild
        	  right = parseQuestionNode( currentElt );
        	  count = 0;
          }
        }
      }
      
      
      // create node
      BinaryTreeNode<String> questionNode = new DefaultBinaryTreeNode<String>();
      questionNode.setData(question);
      // set left and right children
      questionNode.setLeftChild( left );
      questionNode.setRightChild( right );
      
      return questionNode;
    
  }

  }
}


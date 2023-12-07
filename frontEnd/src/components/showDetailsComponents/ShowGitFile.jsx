export default function ShowGitFile({treeNode}) {
    
    
    console.log("print*************************");
    console.log(treeNode);
    console.log(treeNode.length);
    
    return(
        <div  className="your-box-class">
            {treeNode.length === 0 ? null : treeNode.map((conceptItem,index) => (
            
                <div key={index+1}>

            <p>
          <strong>-----------------------------------</strong>
        </p>       
        <p>
          <strong>File Name:</strong> {conceptItem.nameOfTheFile || 'N/A'}
        </p>
        <p>
          <strong>Is Blob:</strong> {conceptItem.isBlob ? 'Yes' : 'No'}
        </p>
        <p>
          <strong>Content:</strong> {conceptItem.contentOfTheFile || 'N/A'}
        </p>
        <p>
          <strong>Blob:</strong> {conceptItem.blob ? 'Yes' : 'No'}
        </p>
        <p>
          <strong>Author:</strong> {conceptItem.author || 'N/A'}
        </p>
        <p>
          <strong>Date:</strong> {conceptItem.date || 'N/A'}
        </p>
      </div>
      
            ))}
        
        </div>
    );
}

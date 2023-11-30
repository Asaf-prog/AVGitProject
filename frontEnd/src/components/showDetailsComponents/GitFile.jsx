export default function GitFile({ treeOrBlob }) {
    // Add conditional rendering or default values
    if (!treeOrBlob) {
      return <p>No file data available</p>;
    }
  
    return (
      <div>
        <p>
          <strong>File Name:</strong> {treeOrBlob.nameOfTheFile || 'N/A'}
        </p>
        <p>
          <strong>Is Blob:</strong> {treeOrBlob.isBlob ? 'Yes' : 'No'}
        </p>
        <p>
          <strong>Content:</strong> {treeOrBlob.contentOfTheFile || 'N/A'}
        </p>
        <p>
          <strong>Blob:</strong> {treeOrBlob.blob ? 'Yes' : 'No'}
        </p>
        <p>
          <strong>Author:</strong> {treeOrBlob.author || 'N/A'}
        </p>
        <p>
          <strong>Date:</strong> {treeOrBlob.date || 'N/A'}
        </p>
      </div>
    );
  }
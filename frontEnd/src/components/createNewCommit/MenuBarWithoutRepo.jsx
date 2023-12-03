import { useState } from 'react';
import '../createNewRepo/MenuBar.css'; 

export default function MenuBarWithoutRepo({userPassword,userName}){
    const [userInput, setUserInput] = useState({
        path: '',
        repoName: '',
        comment: '',
      });
    
        const [path, setPath] = useState('');
        const [nameOfRepo, setNameOfRepo] = useState('');
        const [commentOfRepo, setCommentOfRepo] = useState('');
        const [successMessage, setSuccessMessage] = useState('');
      
        const handlePathChange = (e) => {
          setPath(e.target.value);
          setUserInput({
            ...userInput,
            userName: e.target.value,
          })
        };
    
      
        const handleNameOfRepoChange = (e) => {
          setNameOfRepo(e.target.value);
          setUserInput({
            ...userInput,
            userName: e.target.value,
          })
        };
        const handleCommentOfRepoChange = (e) => {
          setCommentOfRepo(e.target.value);
          setUserInput({
            ...userInput,
            userName: e.target.value,
          })
        };
      
        const handleFormSubmit = (e) => {
          e.preventDefault();
          // You can handle the form submission logic here
          console.log('Submitted values:', { path, nameOfRepo });
          // You may want to reset the form fields after submission
          setPath('');
          setNameOfRepo('');
        };
    
        const handleSubmit = async () =>{
    
          const url = "http://localhost:8080/AvGit/gitCommit";
    
          const gitInitDTO = {
            path: path, 
            repoName: nameOfRepo,
            author: userName,
            comment: commentOfRepo,
            password: userPassword.password
        };
    
        try {
     
          const response = await fetch(url, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body:JSON.stringify(gitInitDTO)
          });
        
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
        
          const responseData = await response.json();
          console.log('Response from http://localhost:8080/AvGit/gitCommit', responseData);
          // If the server returns a 200 status code, set the success message
          setSuccessMessage('Success!');
         
         
        }catch(error){
          console.log('An error occurred: ', error.message);
          setSuccessMessage(''); // Reset success message in case of an error
       
        }
    
        }
    
        return (
          <div className="menu-bar-container">
            <div className="menu-bar-box">
              <form onSubmit={handleFormSubmit}>
                <label>
                  Path:
                  <input
                    type="text"
                    value={path}
                    onChange={handlePathChange}
                    placeholder="Enter folder path"
                  />
                </label>
                <label>
                  Name of Repository:
                  <input 
                  type="text" 
                  value={nameOfRepo} 
                  onChange={handleNameOfRepoChange}
                  placeholder="Enter Name of Repository" 
                  />
                </label>
                <label>
                  Comment:
                  <input type="text" 
                  value={commentOfRepo} 
                  onChange={handleCommentOfRepoChange}
                  placeholder="Leave a Comment" 
                  />
                </label>
                <button  onClick={handleSubmit}>Submit</button>
              </form>
              {successMessage && <p>{successMessage}</p>}
            </div>
          </div>
        );
    
}



    
  



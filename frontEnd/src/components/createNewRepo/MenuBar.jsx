import { useState } from 'react';
import './MenuBar.css'; 

export default function MenuBar({ userPassword }) {
  const [userInput, setUserInput] = useState({
    path: '',
    repoName: '',
    comment: '',
  });

  const [path, setPath] = useState('');
  const [nameOfRepo, setNameOfRepo] = useState('');
  const [commentOfRepo, setCommentOfRepo] = useState('');
  const [successMessage, setSuccessMessage] = useState(false);
  const [isFormComplete, setIsFormComplete] = useState(false);

  const handlePathChange = (e) => {
    setPath(e.target.value);
    setUserInput({
      ...userInput,
      userName: e.target.value,
    });
    checkFormCompleteness();
  };

  const handleNameOfRepoChange = (e) => {
    setNameOfRepo(e.target.value);
    setUserInput({
      ...userInput,
      userName: e.target.value,
    });
    checkFormCompleteness();
  };

  const handleCommentOfRepoChange = (e) => {
    setCommentOfRepo(e.target.value);
    setUserInput({
      ...userInput,
      userName: e.target.value,
    });
    checkFormCompleteness();
  };

  const checkFormCompleteness = () => {
    // Check if all fields are filled
    setIsFormComplete(path.trim() !== '' && nameOfRepo.trim() !== '' && commentOfRepo.trim() !== '');
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    // You can handle the form submission logic here
    console.log('Submitted values:', { path, nameOfRepo });
    // You may want to reset the form fields after submission
    setPath('');
    setNameOfRepo('');
    setCommentOfRepo('');
    setSuccessMessage(false);
  };

  const handleSubmit = async () => {
    // Check if the form is complete before submitting
    if (!isFormComplete) {
      console.log('Please fill in all fields.');
      return;
    }

    const url = "http://localhost:8080/AvGit/gitInit";

    const gitInitDTO = {
      path: path,
      repoName: nameOfRepo,
      comment: commentOfRepo,
      userPassword: userPassword.password
    };

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(gitInitDTO)
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      // If the server returns a 200 status code, set the success message
      const responseData = await response.json();
      console.log('Response from http://localhost:8080/AvGit/gitInit', responseData);

      setSuccessMessage(true);

      // Hide success message after 3 seconds
      setTimeout(() => {
        setSuccessMessage(false);
        console.log("timeout");
      }, 2000);
    } catch (error) {
      console.log('An error occurred: ', error.message);
      setSuccessMessage(false); // Reset success message in case of an error
    }
  };

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
            <input
              type="text"
              value={commentOfRepo}
              onChange={handleCommentOfRepoChange}
              placeholder="Leave a Comment"
            />
          </label>
          <button onClick={handleSubmit} disabled={!isFormComplete}>
            Submit
          </button>
          {successMessage ? <p className="success-message">Success!</p> : " "}
        </form>
      </div>
    </div>
  );
}

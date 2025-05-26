import React from "react";
import './App.css';

class App extends React.Component {

  // ctor
  constructor(props) {
    super(props);
    this.state = {
      items: [],
      DataisLoaded: false
    };
  }

  // componentDidMount to execute the code
  componentDidMount() {
    fetch("http://localhost:8080/api/v1/user/hello")
    .then((res) => res.json())
    .then((json) => {
      this.setState({
        items: json,
        DataisLoaded: true
      });
    })
  }

  render() {
    const { DataisLoaded, items } = this.state;
    if (!DataisLoaded) return <div>
      <h1> Please wait... </h1>
    </div>;

    return (
      <div className = "App">
        <h1> Fetched data from api in react </h1>
        <ol key = {items.id} >
          Full Name: { items.name }
        </ol>
      </div>
    );
  }
}

export default App;

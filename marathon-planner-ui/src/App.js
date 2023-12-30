import Header from "./components/Header.js";
import RunnerForm from "./components/RunnerForm.js";
import TrainingPlan from "./components/TrainingPlan.js";
import axios from "axios";
import { useState } from "react";

function App() {
  const [skillLevel, setSkillLevel] = useState(1.0);
  const [weeklyIncrease, setWeeklyIncrease] = useState(10);
  const [useMiles, setUseMiles] = useState(false);
  const [trainingPlan, setTrainingPlan] = useState([]);

  const generateTrainingSchedule = async (e) => {
    e.preventDefault();

    let requestBody = {
      "skillLevel": skillLevel,
      "weeklyIncrease": weeklyIncrease * 0.01,
      "isMetric": !useMiles
    }

    let response = await axios.post("/trainingSchedule", requestBody);

    setTrainingPlan(response.data);
  }

  const getTrainingSchedulePDF = async (e) => {
    e.preventDefault();

    let response = await axios.post("/pdf");

    console.log("pdf: " + response);
  }

  return (
    <div className="App">
      <Header />
      <RunnerForm onClick={generateTrainingSchedule} skillLevel={skillLevel} setSkillLevel={(e) => setSkillLevel(e.target.value)}
        weeklyIncrease={weeklyIncrease} setWeeklyIncrease={(e) => setWeeklyIncrease(e.target.value)} useMiles={useMiles}
        setUseMiles={(e) => setUseMiles(e.target.checked)} />
      <TrainingPlan trainingPlan={trainingPlan} pdfOnClick={getTrainingSchedulePDF} isMetric={!useMiles} />
    </div>
  );
}

export default App;

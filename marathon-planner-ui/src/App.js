import Header from "./components/Header.js";
import RunnerForm from "./components/RunnerForm.js";
import TrainingPlan from "./components/TrainingPlan.js";
import { useState } from "react";
import axios from "axios";

function App() {
  const [skillLevel, setSkillLevel] = useState(1.0);
  const [weeklyIncrease, setWeeklyIncrease] = useState(10);
  const [useMiles, setUseMiles] = useState(false);
  const [trainingPlan, setTrainingPlan] = useState([]);

  const [isTrainingPlanMetric, setIsTrainingPlanMetric] = useState(true); // only used after a training plan is created

  const generateTrainingSchedule = async (e) => {
    e.preventDefault();

    let requestBody = {
      "skillLevel": skillLevel,
      "weeklyIncrease": weeklyIncrease * 0.01,
      "isMetric": !useMiles
    }

    let response = await axios.post("/trainingSchedule", requestBody);

    setTrainingPlan(response.data);

    // used to display generated training plan in the right system
    setIsTrainingPlanMetric(!useMiles);
  }

  return (
    <div className="App m-2">
      <Header />
      <RunnerForm onClick={generateTrainingSchedule} skillLevel={skillLevel} setSkillLevel={(e) => setSkillLevel(e.target.value)}
        weeklyIncrease={weeklyIncrease} setWeeklyIncrease={(e) => setWeeklyIncrease(e.target.value)} useMiles={useMiles}
        setUseMiles={(e) => setUseMiles(e.target.checked)} />
      <hr></hr>
      <TrainingPlan trainingPlan={trainingPlan} isMetric={isTrainingPlanMetric} />
    </div>
  );
}

export default App;

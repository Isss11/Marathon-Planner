import Header from "./components/Header.js";
import RunnerForm from "./components/RunnerForm.js";
import TrainingPlan from "./components/TrainingPlan.js";
import { useState } from "react";
import axios from "axios";

function App() {
  const [goalDistance, setGoalDistance] = useState("Marathon");
  const [startingWeeklyDistance, setStartingWeeklyDistance] = useState(1.0);
  const [weeklyIncrease, setWeeklyIncrease] = useState(10);
  const [useMiles, setUseMiles] = useState(false);
  const [trainingPlan, setTrainingPlan] = useState([]);

  const [isTrainingPlanMetric, setIsTrainingPlanMetric] = useState(true); // only used after a training plan is created

  const getDistanceFromString = (distanceString) => {
    switch (distanceString) {
      case "5 KM":
        return 5;
      case "10 KM":
        return 10;
      case "Half Marathon":
        return 21.1;
      default:
        return 42.2;
    }
  }

  const generateTrainingSchedule = async (e) => {
    e.preventDefault();

    let requestBody = {
      "startingWeeklyDistance": startingWeeklyDistance,
      "weeklyIncrease": weeklyIncrease * 0.01,
      "isMetric": !useMiles,
      "goalDistance": getDistanceFromString(goalDistance)
    }

    let response = await axios.post("/trainingSchedule", requestBody);

    setTrainingPlan(response.data);

    // used to display generated training plan in the right system
    setIsTrainingPlanMetric(!useMiles);
  }

  return (
    <div className="App m-2">
      <Header />
      <RunnerForm onClick={generateTrainingSchedule} goalDistance={goalDistance} setGoalDistance={(e) => setGoalDistance(e.target.value)} startingWeeklyDistance={startingWeeklyDistance}
        setStartingWeeklyDistance={(e) => setStartingWeeklyDistance(e.target.value)} weeklyIncrease={weeklyIncrease} setWeeklyIncrease={(e) => setWeeklyIncrease(e.target.value)}
        useMiles={useMiles} setUseMiles={(e) => setUseMiles(e.target.checked)} />
      <hr></hr>
      <TrainingPlan trainingPlan={trainingPlan} isMetric={isTrainingPlanMetric} />
    </div>
  );
}

export default App;

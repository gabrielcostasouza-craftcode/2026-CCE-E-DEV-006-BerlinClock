import './App.css'
import { BerlinClockDisplay } from './components/BerlinClockDisplay'

function App() {
  const rows = ['O', 'RRRO', 'ROOO', 'YYRYYRYYRYO', 'OOOO']

  return (
    <>
      <BerlinClockDisplay rows={rows}/>
    </>
  )
}

export default App

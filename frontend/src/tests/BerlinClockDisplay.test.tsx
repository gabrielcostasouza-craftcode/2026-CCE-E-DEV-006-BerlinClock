import { render, screen, within } from '@testing-library/react'
import { BerlinClockDisplay } from '../components/BerlinClockDisplay'

const lampLabels = (rowName: string) =>
  within(screen.getByRole('group', { name: rowName }))
    .getAllByRole('img')
    .map((lamp) => lamp.getAttribute('aria-label'))

describe('BerlinClockDisplay', () => {
  const rows = ['Y', 'RRRO', 'ROOO', 'YYRYYRYYRYO', 'OOOO']

  it('Renders all 24 lamps', () => {
    render(<BerlinClockDisplay rows={rows} />)

    expect(screen.getAllByRole('img')).toHaveLength(24)
  })

  it('Renders rows correctly', () => {
    render(<BerlinClockDisplay rows={rows} />)

    expect(lampLabels('seconds')).toEqual(['Y on'])
    expect(lampLabels('five hours')).toEqual(['R on', 'R on', 'R on', 'off'])
    expect(lampLabels('single hours')).toEqual(['R on', 'off', 'off', 'off'])
    expect(lampLabels('five minutes')).toEqual([
      'Y on',
      'Y on',
      'R on',
      'Y on',
      'Y on',
      'R on',
      'Y on',
      'Y on',
      'R on',
      'Y on',
      'off',
    ])
    expect(lampLabels('single minutes')).toEqual(Array(4).fill('off'))
  })
})

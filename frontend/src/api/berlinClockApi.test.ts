import { fetchBerlinTime } from './berlinClockApi'

const clock = {
  digitalTime: '16:50:06',
  berlinTime: 'YRRROROOOYYRYYRYYRYOOOOO',
  rows: ['Y', 'RRRO', 'ROOO', 'YYRYYRYYRYO', 'OOOO'],
}

const respondWith = (status: number, body: unknown) =>
  vi.fn().mockResolvedValue(new Response(JSON.stringify(body), { status }))

describe('berlinClockApi', () => {
  afterEach(() => {
    vi.unstubAllGlobals()
  })

  it('Digital time to Berlin time', async () => {
    const fetchMock = respondWith(200, clock)
    vi.stubGlobal('fetch', fetchMock)

    await expect(fetchBerlinTime('16:50:06')).resolves.toEqual(clock)
    expect(fetchMock).toHaveBeenCalledWith('/api/to-berlin-time?time=16%3A50%3A06')
  })
})

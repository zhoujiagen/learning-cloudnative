package future

import (
	"context"
	"sync"
	"time"
)

// Future: the interface that is received by the consumer to retrieve the eventual result.
// SlowFunction: A wrapper function around some function to be asynchronously executed; provides Future.
// InnerFuture: satisfies the Future interface; includes an attached method that containers result access logic.

type Future interface {
	Result() (string, error)
}

type InnerFuture struct {
	once sync.Once
	wg   sync.WaitGroup

	res   string
	err   error
	resCh <-chan string
	errCh <-chan error
}

func (f *InnerFuture) Result() (string, error) {
	f.once.Do(func() {
		f.wg.Add(1)
		defer f.wg.Done()

		f.res = <-f.resCh
		f.err = <-f.errCh
	})

	f.wg.Wait()
	return f.res, f.err
}

func SlowFunction(ctx context.Context) Future {
	resCh := make(chan string)
	errCh := make(chan error)

	go func() {
		select {
		case <-time.After(time.Second * 2):
			resCh <- "I slept for 2 seconds"
			errCh <- nil
		case <-ctx.Done():
			resCh <- ""
			errCh <- ctx.Err()
		}
	}()

	return &InnerFuture{resCh: resCh, errCh: errCh}
}

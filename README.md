#### RxJavaDemo

The main purpose of this app is to show real-world useful examples of using RxJava with Android.

#### What is RxJava & RxAndroid

RxJava is a library based on ReactiveX meant for easy developers to deal with thread and asynchronous tasks. RxAndroid is just a layer on top of RxJava which provide android specific support to run it in Android applications.

#### Why RxJava

We use RxJava for multithreading, managing background tasks and removing callback hells. We can solve so many complex use-cases with the help of the RxJava. It enables us to do complex things very simple. It provides us the power.

Managing multiple Async tasks is pain the neck and so is managing threads in Android. Without proper thread management there can be memory leaks which sometimes might lead to your app crashing. That’s why RxJava comes with a built-in thread management system.

#### Key Concepts

Observable - Emits items (the stream)
Observer - Consumes those items
Operators - Allows you manipulate the data emitted by Observables. Basically, operators tells Observable, how to modify the data and when to emit the data. Using the operators you can modify, merge, filter or group the data streams

Observers can take actions when an Observable emits a value, when the Observable says an error has occurred, or when the Observable says that it no longer has any values to emit. All three of these actions are encapsulated in the Observer interface. The corresponding functions are `onNext()`, onError(), and onCompleted().

onNext(T) - invoked when an item is emitted from the stream
onError(Throwable) - invoked when an error has occurred within the stream
onCompleted() - invoked when the stream is finished emitting items.

#### Dependencies 

def rxjava_version ="2.1.8"
def rxandroid_version = "2.0.1"

dependencies 
{
	implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"
         implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"
}


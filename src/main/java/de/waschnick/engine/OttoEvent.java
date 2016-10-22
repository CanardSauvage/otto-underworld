package de.waschnick.engine;


// To address a comment question on how do you know which objects should get the message: The objects themselves should Request to be notified about events. Your EventMessagingSystem (EMS) will need a Register(int iEventId, IEventMessagingSystem * pOjbect, (EMSCallback)fpMethodToUseForACallback) as well as a matching Unregister (making a unique entry for an iEventId out of the object pointer and callback). This way, when an object wants to know about a message it can Register() with the system. When it no longer needs to know about the events, it can Unregister(). Clearly, you'd want a pool of these callback registration objects and an effective way to add/remove them from lists. (I've usually used self ordering arrays; a fancy way of saying they track their own allocations between a pool stack of unused objects and arrays that shift their sizes in place when needed).


public interface OttoEvent {

    void register(OttoEventLover ottoEventLover);

    void unregister(OttoEventLover ottoEventLover);
}

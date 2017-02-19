using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CarTraverse : MonoBehaviour {
	public float amplitude;
	public int level;
	public float forwardSpeed;
	public float turnSpeed;
	public float obstacleFrequency;
	public GameObject prefabCar;
	public GameObject glowParticles;
	const int STATE_INIT = 0;
	const int STATE_FORWARD = 1;
	const int STATE_TURN = 2;
	const int STATE_END = 3;
	private int state;

	// Use this for initialization
	void Start () {
		state = STATE_TURN;
		StartCoroutine(dropObjects (obstacleFrequency));
		StartCoroutine (checkDirection ());
		if (obstacleFrequency == 0)
			obstacleFrequency = Mathf.Sin (Time.time);
	}
	
	// Update is called once per frame
	void Update () {
		bobUpAndDown ();
		//controlSpeed ();
		switch (state) {
		case (STATE_FORWARD): 
			moveForward (forwardSpeed);
			break;
		case (STATE_TURN):
			moveTurn (turnSpeed);
			break;
		default:
			break;
		}
	}
	void bobUpAndDown () {
		gameObject.transform.Translate(0,  Mathf.Cos(Time.time) * amplitude, 0);
		gameObject.transform.Rotate (4 * Mathf.Cos (Time.time - 2f) * amplitude, 0, 0);
	}



	IEnumerator dropObjects (float frequency) {
		for(;;) {
			Instantiate (prefabCar, (new Vector3(this.transform.position.x + Random.value/2, -1f,this.transform.position.z + Random.value/2)), gameObject.transform.rotation	); 
			yield return new WaitForSeconds (1f / frequency);
		}
	}

	IEnumerator checkDirection() {
		for(;;) {
			switch(state) {
			case STATE_FORWARD: 
				state = STATE_TURN;
				break;
			case STATE_TURN:
				state = STATE_FORWARD;
				break;
			default:
				state = STATE_FORWARD;
				break;
			}
			yield return new WaitForSeconds (0.5f);
		}
	}

	void moveForward(float speed) {
		gameObject.transform.Translate (0, 0, speed * Time.deltaTime);
	}

	void moveTurn(float speed) {
		float deltaAngle = speed * Time.deltaTime * 180 * (Mathf.Sin (Time.time) + Mathf.Sin (Time.time * 0.2931f));
		deltaAngle = Time.deltaTime * speed * 180;
		gameObject.transform.Rotate (0, deltaAngle, 0);
		gameObject.transform.Translate (0, 0, speed * Time.deltaTime);
	}
	void controlSpeed() {
		forwardSpeed += Time.deltaTime / 50;
		turnSpeed += Time.deltaTime / 50;
	}

}

using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScoreHandler : MonoBehaviour {
	public GameObject gameOverUI;
	private bool isGameOver;
	public int maxHealth;
	private int remainingHealth;
	// Use this for initialization
	void Start () {
		isGameOver = false;
		gameOverUI.SetActive (false);
		remainingHealth = maxHealth;
	}

	// Update is called once per frame
	void Update () {
		//gameObject.transform.position = Camera.current.transform.position;
		Vector3 distance = (gameObject.transform.position - GameObject.FindWithTag ("Fairy").transform.position);
		if (distance.magnitude > 5) {
			loseHealth ();
		}
	}
	void OnCollisionEnter (Collision col) {
		//Debug.Log ("hit");
		if (col.gameObject.tag == "Obstacle") {
			loseHealth ();
		}
	}

	void loseHealth() {
		if (!isGameOver) {
			remainingHealth--;
			if (remainingHealth == 0) {
				gameOverUI.SetActive (true);
				isGameOver = true;
			}
		}
	}
}



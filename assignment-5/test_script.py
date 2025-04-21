import subprocess

# Expected output to compare (excluding the input prompt)
expected_output = """Selected Connections:
FAU---FGCU\t$180
FAU---FIU\t$220
FGCU---UWF\t$370
FIU---FSU\t$200
FSU---UF\t$160
UCF---UF\t$120

Total Cost: $1250"""

# Step 1: Compile the Java files
compile = subprocess.run(["javac", "CampusNetworkPlanner.java", "CampusDriver.java"], capture_output=True, text=True)

if compile.returncode != 0:
    print("Compilation Failed:\n")
    print(compile.stderr)
else:
    print("Compilation Successful")

    # Step 2: Run the program with the input file name
    try:
        process = subprocess.run(
            ["java", "CampusDriver"],
            input="Florida.txt\n",
            capture_output=True,
            text=True,
            timeout=5
        )

        full_output = process.stdout.strip()

        # Step 3: Extract only the portion after 'Selected Connections:'
        keyword = "Selected Connections:"
        if keyword in full_output:
            selected_output = full_output[full_output.index(keyword):].strip()
        else:
            selected_output = "(Missing 'Selected Connections:' section)"

        # Step 4: Normalize and compare
        print("\n--- Student Output (Trimmed) ---")
        print(selected_output)

        print("\n--- Expected Output ---")
        print(expected_output)

        if selected_output == expected_output:
            print("\nOutput matches expected!")
        else:
            print("\nOutput does not match.")

    except subprocess.TimeoutExpired:
        print("Program took too long to run.")
